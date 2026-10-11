package io.github.projetos_consoles.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import io.github.projetos_consoles.ElevadorGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new ElevadorGame(), getDefaultConfiguration());
    }
    // Evitar mexer aqui p/ não dar erro!
    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Elevador");
        configuration.useVsync(true);
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        configuration.setWindowedMode(900, 800);
        return configuration;
    }
}
