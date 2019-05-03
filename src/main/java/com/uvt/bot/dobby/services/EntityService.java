package com.uvt.bot.dobby.services;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.messageType.TextMessage;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.ShopItemDTO;
import com.uvt.bot.dobby.model.entity.DobbyList;
import com.uvt.bot.dobby.model.entity.DobbyListItem;
import com.uvt.bot.dobby.repository.DobbyListRepository;
import com.uvt.bot.dobby.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntityService {

    private DobbyListRepository dobbyListRepository;

    @Autowired
    public EntityService(DobbyListRepository dobbyListRepository){
        this.dobbyListRepository = dobbyListRepository;
    }

    public void createList(RecastRequestDTO recastRequestDTO){
        DobbyList dobbyList = dobbyListRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseGet(()->new DobbyList(recastRequestDTO.getNlp().getTimestamp()));

        List<ShopItemDTO> itemList = Optional.ofNullable(recastRequestDTO.getNlp().getEntities().getShop_item())
                .orElseGet(Collections::emptyList);

        List<DobbyListItem> dobbyListItems = itemList.stream()
                .map(shopItemDTO -> new DobbyListItem(shopItemDTO.getValue()))
                .collect(Collectors.toList());

        dobbyListItems.forEach(dobbyListItem -> dobbyList.getItems().add(dobbyListItem));
        dobbyList.setId(recastRequestDTO.getConversation().getId());
        dobbyListRepository.save(dobbyList);
    }

    public RecastReply getList(RecastRequestDTO recastRequestDTO){
        DobbyList dobbyList = dobbyListRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseGet(()->new DobbyList(recastRequestDTO.getNlp().getTimestamp()));

        String itemString = dobbyList.getItems().stream()
                .map(DobbyListItem::getItem)
                .collect(Collectors.joining( ", " ));

        RecastReply recastReply = new RecastReply(new ArrayList<>());
        TextMessage textMessage = new TextMessage(itemString);
        TextMessage textMessage1 = new TextMessage("Your List:");
        recastReply.getReplies().add(textMessage1);
        recastReply.getReplies().add(textMessage);
        return recastReply;
    }

    public RecastReply deleteList(RecastRequestDTO recastRequestDTO){
        DobbyList dobbyList = dobbyListRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseThrow(()-> EntityNotFoundException.withMessage("No lists were found"));

        dobbyListRepository.delete(dobbyList);
        RecastReply recastReply = new RecastReply(new ArrayList<>());
        TextMessage textMessage = new TextMessage("List was erased");
        recastReply.getReplies().add(textMessage);
        return recastReply;
    }

}
