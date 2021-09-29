package com.github.martinfrank.eobedit.data;

import com.github.martinfrank.eobedit.event.ChangeEventType;
import com.github.martinfrank.eobedit.event.PlayerDataChangeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class SavegameFile {

    public static final int AMOUNT_CHARACTERS = 6;
    public static final int CHARACTER_DATA_LENGTH = 243;


    private static final Logger LOGGER = LoggerFactory.getLogger(SavegameFile.class);

    private File file;
    private byte[] content;
    private final PlayerData[] playerData = new PlayerData[AMOUNT_CHARACTERS];
    private boolean hasUnsavedChanges = false;
    private PlayerDataChangeEventListener listener;

    public SavegameFile() {

    }

//    public SavegameFile(String filename) throws IOException {
//        if(filename == null){
//            throw new IllegalArgumentException("filename must be provided");
//        }
//        file = new File(filename);
//        if(!file.exists()){
//            throw new IllegalArgumentException("file "+filename+" does not exist");
//        }
//        reload();
//    }

    public void load(String filename) throws IOException {
        if(filename == null){
            throw new IllegalArgumentException("filename must be provided");
        }
        File file = new File(filename);
        load(file);
        if(!file.exists()){
            throw new IllegalArgumentException("file "+filename+" does not exist");
        }
        reload();
    }

    public void load(File file) throws IOException {
        this.file = file;
        if(!file.exists()){
            throw new IllegalArgumentException("file "+file.getName()+" does not exist");
        }
        reload();
    }

    public void reload() throws IOException {
        content = Files.readAllBytes(file.toPath());
        copyContentIntoPlayerData();
        Arrays.stream(playerData).forEach(p -> p.setPlayerDataChangeListener(listener));
        listener.playerDataChanged(0, ChangeEventType.LOAD_DATA);
        LOGGER.debug("successfully loaded file {}", file.getName());
    }

    private void copyContentIntoPlayerData() {
        for(int i = 0; i < AMOUNT_CHARACTERS; i ++){
            int position = i * CHARACTER_DATA_LENGTH;
            playerData[i] = new PlayerData(i, ByteArrayTool.copy(content, position, CHARACTER_DATA_LENGTH));
        }
    }

    public void save() throws IOException {
        copyPlayerDataIntoContent();
        Files.write(file.toPath(), content, StandardOpenOption.WRITE);
        Arrays.stream(playerData).forEach(PlayerData::resetHasUnsavedChanged);
        LOGGER.debug("successfully written file {}", file.getName());
    }

    private void copyPlayerDataIntoContent() {
        for(int i = 0; i < AMOUNT_CHARACTERS; i ++){
            int position = i * CHARACTER_DATA_LENGTH;
            System.arraycopy(playerData[i].getContent(), 0, content, position, CHARACTER_DATA_LENGTH);
        }
    }

    public PlayerData getPlayer(int i) {
        return playerData[i];
    }

    public boolean hasUnsavedChanges(){
        if(content == null){
            return false;
        }
        return Arrays.stream(playerData).anyMatch(PlayerData::hasUnsavedChanges);
    }

    public void registerChangeListener(PlayerDataChangeEventListener listener) {
        this.listener = listener;

    }
}
