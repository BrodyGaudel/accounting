package org.mounanga.accounting.commands.command;

import java.time.LocalDateTime;

public class DeleteLiabilityCommand extends BaseCommand<String> {
    public DeleteLiabilityCommand(String id, String commandBy, LocalDateTime commandDateTime) {
        super(id, commandBy, commandDateTime);
    }
}
