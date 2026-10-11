package io.github.projetos_consoles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

public class ElevadorGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture imgFundo;
    private Texture imgPortaFechada;
    private Texture imgPortaAberta;
    private Texture[] imgPoneis;
    private String[] nomesPoneis = {"twlight.png", "rainbow.png", "rarity.png", "applejack.png", "fluttershy.png", "pinkpie.png"};
    private Elevador elevador;
    private Passageiro[] passageiros;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 900, 800);
        
        batch = new SpriteBatch();
        imgFundo = new Texture("background.png");
        imgPortaFechada = new Texture("porta_fechada.png");
        imgPortaAberta = new Texture("porta_aberta.png");
        
        imgPoneis = new Texture[6];
        for(int i = 0; i < 6; i++) {
            imgPoneis[i] = new Texture(nomesPoneis[i]);
        }

        Predio.inicializar();
        elevador = new Elevador();
        elevador.start();

        passageiros = new Passageiro[6];
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int origem = rand.nextInt(7);
            int destino = rand.nextInt(7);
            while (destino == origem) { destino = rand.nextInt(7); }
            passageiros[i] = new Passageiro(nomesPoneis[i], origem, destino);
            passageiros[i].start();
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined); 

        batch.begin();
        batch.draw(imgFundo, 0, 0, 900, 800);

        // --- ELEVADOR MÁGICO ---
        float larguraElevador = 900; 
        float alturaElevador = 850;
        
        // Centralizar as portas no eixo do filete roxo
        float offsetElevadorX = (larguraElevador / 2) - 60; 
        float offsetElevadorY = (alturaElevador / 2) - 40; 
        
        if (elevador.portaAberta) {
            batch.draw(imgPortaAberta, elevador.x - offsetElevadorX, elevador.y - offsetElevadorY, larguraElevador, alturaElevador);
        } else {
            batch.draw(imgPortaFechada, elevador.x - offsetElevadorX, elevador.y - offsetElevadorY, larguraElevador, alturaElevador);
        }

        // --- PÔNEIS ---
        float larguraPonei = 850;
        float alturaPonei = 850;
        float offsetPoneiX = (larguraPonei / 2) - 25;
        float offsetPoneiY = (alturaPonei / 2) - 20; // tentativa de fazer a pônei ficar no chão

        for (int i = 0; i < 6; i++) {
            Passageiro p = passageiros[i];
            if (p != null) {
                Texture tex = obterTexturaPonei(p.nomeTextura);
                
                if (p.estado == 1) { 

                    int indexNoElevador = Predio.passageirosNoElevador.indexOf(p);
                    float slotX = elevador.x - 30 + (indexNoElevador * 40); 
                    batch.draw(tex, slotX - offsetPoneiX, elevador.y - offsetPoneiY, larguraPonei, alturaPonei);
                } else {

                    batch.draw(tex, p.x - offsetPoneiX, p.y - offsetPoneiY, larguraPonei, alturaPonei);
                }
            }
        }
        
        batch.end();
    }

    private Texture obterTexturaPonei(String nome) {
        for(int i = 0; i < 6; i++) {
            if(nomesPoneis[i].equals(nome)) return imgPoneis[i];
        }
        return imgPoneis[0];
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgFundo.dispose();
        imgPortaFechada.dispose();
        imgPortaAberta.dispose();
        for(Texture t : imgPoneis) t.dispose();
    }
}