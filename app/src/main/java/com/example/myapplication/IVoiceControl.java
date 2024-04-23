package com.example.myapplication;

public interface IVoiceControl {
    public abstract void processVoiceCommands(String... voiceCommands); 
    
    public void restartListeningService(); 
}
