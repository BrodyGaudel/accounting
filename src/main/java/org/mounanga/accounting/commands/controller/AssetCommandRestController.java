package org.mounanga.accounting.commands.controller;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.commands.command.CreateAssetCommand;
import org.mounanga.accounting.commands.command.DeleteAssetCommand;
import org.mounanga.accounting.commands.command.UpdateAssetCommand;
import org.mounanga.accounting.commands.dto.AssetRequestDTO;
import org.mounanga.accounting.commands.util.CommandFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/assets")
public class AssetCommandRestController {

    private final CommandGateway commandGateway;

    public AssetCommandRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody @Valid AssetRequestDTO dto){
        CreateAssetCommand command = CommandFactory.create(dto, getCommander());
        return commandGateway.send(command);
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> create(@PathVariable String id, @RequestBody @Valid AssetRequestDTO dto){
        UpdateAssetCommand command = CommandFactory.create(dto, id, getCommander());
        return commandGateway.send(command);
    }

    @PutMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        DeleteAssetCommand command = CommandFactory.create(id, getCommander(), LocalDateTime.now());
        return commandGateway.send(command);
    }

    @NotNull
    @Contract(pure = true)
    private String getCommander(){
        return "get_current_username_with_spring_security";
    }

}
