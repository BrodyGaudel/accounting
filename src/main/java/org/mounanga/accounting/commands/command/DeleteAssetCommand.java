package org.mounanga.accounting.commands.command;

import java.time.LocalDateTime;

public class DeleteAssetCommand extends BaseCommand<String> {
    public DeleteAssetCommand(String id, String commandBy, LocalDateTime commandDateTime) {
        super(id, commandBy, commandDateTime);
    }
}
