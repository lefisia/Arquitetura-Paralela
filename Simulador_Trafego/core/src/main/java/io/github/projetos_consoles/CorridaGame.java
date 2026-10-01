package io.github.projetos_consoles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CorridaGame extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private Texture[] imagensCarros; 
    private Texture fundoVia;
    private CarroThreadRandom[] carros;

    @Override
    public void create() {
        batch = new SpriteBatch();
        
        // Background da via e cruzamento
        fundoVia = new Texture("background_trafego.png"); 
        
        // Assets dos carrinhos nomeados de acordo com as cores
        imagensCarros = new Texture[5];
        imagensCarros[0] = new Texture("carro_rosa.png");
        imagensCarros[1] = new Texture("carro_azul.png");
        imagensCarros[2] = new Texture("carro_verde.png");
        imagensCarros[3] = new Texture("carro_amarelo.png");
        imagensCarros[4] = new Texture("carro_branco.png");
        
        // Cada carro será uma Thread
        carros = new CarroThreadRandom[5];
        
        // Informações/parâmetros de cada carrinho. Isto, é, cada um terá um nome, aceleração, Posição inicial X, Posição Inicial Y, DirX, DirY e Distância Total
        carros[0] = new CarroThreadRandom("Carro Rosa", 5, -50, 250, 1, 0, 900);
        carros[1] = new CarroThreadRandom("Carro Azul", 6, 800, 310, -1, 0, 900);
        carros[2] = new CarroThreadRandom("Carro Verde", 4, 430, -50, 0, 1, 700);
        carros[3] = new CarroThreadRandom("Carro Amarelo", 7, 330, 600, 0, -1, 700);
        carros[4] = new CarroThreadRandom("Carro Branco", 5, -200, 250, 1, 0, 1050); 
        
        for (int i = 0; i < 5; i++) {
            carros[i].start(); 
        }
    }

    // Neste caso, tive que pesquisar para saber como eu faria a renderização sem erros
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        
        // Renderizar a Via do jogo
        batch.draw(fundoVia, 0, 0, 800, 600);
        
        //Renderização 
        for (int i = 0; i < carros.length; i++) {
            if (carros[i].ativo) {
                batch.draw(imagensCarros[i], carros[i].x, carros[i].y, 40, 40); 
            }
        }
        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        fundoVia.dispose();
        for (Texture img : imagensCarros) {
            img.dispose();
        }
    }
}