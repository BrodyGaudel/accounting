package org.mounanga.accounting.commands.controller;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.accounting.commands.command.CreateLiabilityCommand;
import org.mounanga.accounting.commands.command.DeleteLiabilityCommand;
import org.mounanga.accounting.commands.command.UpdateLiabilityCommand;
import org.mounanga.accounting.commands.dto.LiabilityRequestDTO;
import org.mounanga.accounting.commands.util.CommandFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/liabilities")
public class LiabilityCommandRestController {

    private final CommandGateway commandGateway;

    public LiabilityCommandRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody @Valid LiabilityRequestDTO dto){
        CreateLiabilityCommand command = CommandFactory.create(dto, getCommander());
        return commandGateway.send(command);
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> update(@PathVariable String id, @RequestBody @Valid LiabilityRequestDTO dto){
        UpdateLiabilityCommand command = CommandFactory.create(dto, id,getCommander());
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        DeleteLiabilityCommand command = CommandFactory.create(getCommander(), id);
        return commandGateway.send(command);
    }

    @NotNull
    @Contract(pure = true)
    private String getCommander(){
        return "get_current_username_with_spring_security";
    }
}
