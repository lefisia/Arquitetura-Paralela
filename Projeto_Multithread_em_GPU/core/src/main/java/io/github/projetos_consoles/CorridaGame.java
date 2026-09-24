package io.github.projetos_consoles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CorridaGame extends ApplicationAdapter {
    
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    
    // Array para organizar as imagens dos carros
    private Texture[] imagensCarros; 
    
    private CarroThreadRandom[] carros;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        
        // Lista para receber 5 assets
        imagensCarros = new Texture[5];
        
        // As imagens estarão na pasta assets
        imagensCarros[0] = new Texture("carro_vermelho.jpg");
        imagensCarros[1] = new Texture("carro_azul.jpg");
        imagensCarros[2] = new Texture("carro_verde.jpg");
        imagensCarros[3] = new Texture("carro_amarelo.jpg");
        imagensCarros[4] = new Texture("carro_roxo.jpg");
        
        carros = new CarroThreadRandom[5];
        for (int i = 0; i < 5; i++) {
            carros[i] = new CarroThreadRandom("Carro_0" + (i + 1), 10, 800);
            carros[i].start(); 
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Começo do simulador
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(800, 0, 10, Gdx.graphics.getHeight());
        shapeRenderer.end();

        batch.begin();
        
        for (int i = 0; i < carros.length; i++) {
            float x = carros[i].distanciaPercorrida;
            float y = (i * 100) + 50; 
            
            Texture imagemDesteCarro = imagensCarros[i];
            
            // Desenhamos a imagem específica para este carro
            batch.draw(imagemDesteCarro, x, y, 60, 40); 
        }
        
        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        
        for (int i = 0; i < imagensCarros.length; i++) {
            imagensCarros[i].dispose();
        }
    }
}