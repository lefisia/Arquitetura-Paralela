package io.github.projetos_consoles.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.projetos_consoles.ElevadorGame;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Simulador de Elevador Mágico");
        
        // Background e tela
        config.setWindowedMode(900, 800);
        config.useVsync(true);
        config.setForegroundFPS(60);
        
        new Lwjgl3Application(new ElevadorGame(), config);
    }
}