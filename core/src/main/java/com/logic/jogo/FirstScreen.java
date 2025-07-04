package com.logic.jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    SpriteBatch loteDesenho;
    // Carregar texturas
    Texture jogadorTextura = new Texture("jogador.png");
    Texture laserTextura = new Texture("laser2.png"); // Crie uma textura laser.png
    Texture inimigoTextura;
    Texture obstaculoTextura;
    Texture backgroundTexture;
    Texture portalTextura;
    OrthographicCamera camera;// Declaração da câmara
    Viewport viewport;
    Rectangle portal;// Declaração do portal
    Rectangle jogador;// Declaração do jogador
    ArrayList<Rectangle> obstaculos;
    ArrayList<Rectangle> inimigos;
    ArrayList<Texture> texturas;
    ArrayList<Sound> sons;// Declaração da lista de sons
    ArrayList<Laser> lasers = new ArrayList<>();// Lista de lasers disparados

    // Declare som de colisão e música de fundo
    private Sound somColisao;
    private Music musicaFundo;
    private float rotacaoPortal = 0f;

    // This is the constructor of the screen.
    @Override
    public void show() {
        // Inicializar lote de desenho
        loteDesenho = new SpriteBatch();
        camera = new OrthographicCamera(); // Criar câmara ortográfica
        camera.setToOrtho(false, 800, 600);
        viewport = new StretchViewport(800, 600, camera);// Definir a largura e altura da viewport
        viewport.apply();// Aplicar a viewport

        // Configurar lote de desenho
        loteDesenho.setProjectionMatrix(camera.combined);
        // Definir cor de fundo
        Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta
        // Limpar o ecrã
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        // Inicializar texturas
        jogadorTextura = new Texture("jogador.png");
        inimigoTextura = new Texture("inimigo.png");
        obstaculoTextura = new Texture("planeta.png");
        backgroundTexture = new Texture("background.png");
        portalTextura = new Texture("portal.png"); // carregar textuta do portal
        // Criar retângulo do portal
        portal = new Rectangle();
        portal.x = 580;
        portal.y = 350;
        portal.width = 48;
        portal.height = 48;
        // Criar retângulo do jogador
        jogador = Jogador.criarJogador();// Chamar o método estático para criar o jogador
        inimigos = Inimigo.criarInimigos();// Chamar o método estático para criar inimigos
            ArrayList<Inimigo> inimigos = new ArrayList<>();
            Rectangle inimigo1 = new Rectangle(200, 200, 64, 64);
            inimigos.add(new Inimigo(inimigo1,  new MoverDireto(1)));
            Rectangle inimigo2 = new Rectangle(300, 300, 64, 64);
            inimigos.add(new Inimigo(inimigo2, new MoverAleatorio(1)));
            Rectangle inimigo3 = new Rectangle(400, 400, 64, 64);
            inimigos.add(new Inimigo(inimigo3, new MoverHorizontal(1)));
            Rectangle inimigo4 = new Rectangle(500, 500, 64, 64);
            inimigos.add(new Inimigo(inimigo4, new MoverVertical(1)));

        // Gerar planetas aleatórios
        gerarPlanetasAleatorios();

        // Inicializar texturas
        texturas = new ArrayList<>();
        texturas.add(jogadorTextura);// Adicionar textura do jogador
        texturas.add(inimigoTextura);// Adicionar textura do inimigo
        texturas.add(obstaculoTextura);// Adicionar textura do obstáculo
        // Inicializar sons
        musicaFundo = Gdx.audio.newMusic(Gdx.files.internal("musica_fundo.mp3")); // Substitua pelo nome do seu arquivo
        musicaFundo.setLooping(true); // Para tocar continuamente
        musicaFundo.setVolume(0.5f); // Volume entre 0.0 e 1.0
        musicaFundo.play();// Iniciar a música de fundo
        somColisao = Gdx.audio.newSound(Gdx.files.internal("colisao.mp3"));// Som colisão
        sons = new ArrayList<>();// Inicializar a lista de sons
        // Adicionar sons à lista
        sons.add(Gdx.audio.newSound(Gdx.files.internal("explosao.mp3"))); // Substitua pelo nome do seu arquivo
        sons.add(Gdx.audio.newSound(Gdx.files.internal("laser.mp3")));//Som disparo
        sons.add(somColisao);// Adicionar som de colisão à lista
    }
    // Método para encontrar o inimigo mais próximo do jogador
    private Rectangle encontrarInimigoMaisProximo() {
        Rectangle maisProximo = null;
        float menorDistancia = Float.MAX_VALUE;

        for (Rectangle inimigo : inimigos) {
            float distancia = Vector2.dst(jogador.x, jogador.y, inimigo.x, inimigo.y);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                maisProximo = inimigo;
            }
        }
        return maisProximo;
    }
    // Método para gerar planetas aleatórios
    private void gerarPlanetasAleatorios() {
        obstaculos = new ArrayList<>();
        // Definir constantes para o número de planetas e suas dimensões
        final int NUM_PLANETAS = 7; // ou outro número
        final int PLANETA_LARGURA = 48;
        final int PLANETA_ALTURA = 48;
        // Gerar planetas aleatórios
        for (int i = 0; i < NUM_PLANETAS; i++) {
            Rectangle obstaculo = new Rectangle();
            boolean sobrepoeJogador;
            do {
                float x = MathUtils.random(0, Gdx.graphics.getWidth() - PLANETA_LARGURA);
                float y = MathUtils.random(0, Gdx.graphics.getHeight() - PLANETA_ALTURA);
                obstaculo.set(x, y, PLANETA_LARGURA, PLANETA_ALTURA);
                // Verifica se o obstáculo está sobreposto ao jogador
                sobrepoeJogador = jogador.overlaps(obstaculo);
                // Verifica se o obstáculo está sobreposto a outros obstáculos
                for (Rectangle outroObstaculo : obstaculos) {
                    if (obstaculo.overlaps(outroObstaculo)) {
                        sobrepoeJogador = true; // Se sobrepõe a outro obstáculo, repete o loop
                        break; // Sai do loop se encontrar uma sobreposição
                    }
                }
            } while (sobrepoeJogador);
            obstaculos.add(obstaculo);
        }
        // Garantir que portal não está proximo dos obtaculos
        for (Rectangle obstaculo : obstaculos) {
            // Verificar se o portal está sobreposto a algum obstáculo
            if (portal.overlaps(obstaculo)) {
                // Reposicionar o portal para evitar sobreposição
                portal.x = MathUtils.random(0, Gdx.graphics.getWidth() - portal.width);
                portal.y = MathUtils.random(0, Gdx.graphics.getHeight() - portal.height);
            }
        }

        // Garantir que o portal não está proximo ao jogador
        if (portal.overlaps(jogador)) {
            // Reposicionar o portal para evitar sobreposição com o jogador
            portal.x = MathUtils.random(0, Gdx.graphics.getWidth() - portal.width);
            portal.y = MathUtils.random(0, Gdx.graphics.getHeight() - portal.height);
        }
    }

// This method is called every frame to render the screen.
    @Override
    public void render(float delta) {

        // Limpar o ecrã
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // novo Libgdx game site
        input();
        logic();
        draw();
    }

    private void draw() {
        // Desenhar o ecrã
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);  // Limpar o ecrã
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();// Aplicar a viewport
        loteDesenho.setProjectionMatrix(viewport.getCamera().combined);

        loteDesenho.begin();// Iniciar o lote de desenho
        loteDesenho.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        loteDesenho.draw(jogadorTextura, jogador.x, jogador.y, jogador.width, jogador.height);
        for (Rectangle obstaculo : obstaculos) { // Verificar se "obstaculos" está inicializado e não é null
            loteDesenho.draw(obstaculoTextura, obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height);
        }
        for (Rectangle inimigo : inimigos) {
            loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y, inimigo.width, inimigo.height);
        }
        // Desenhar o portal com rotação
        rotacaoPortal -= 90 * Gdx.graphics.getDeltaTime(); // 90 gr
        loteDesenho.draw(
            portalTextura,
            portal.x + portal.width / 2,  // centro x
            portal.y + portal.height / 2, // centro y
            portal.width / 2,             // origem da rotação x
            portal.height / 2,            // origem da rotação y
            portal.width,                 // largura
            portal.height,                // altura
            1f, 1f,                       // escala x, y
            rotacaoPortal,                // rotação
            0, 0,                         // recorte x, y
            portalTextura.getWidth(),
            portalTextura.getHeight(),
            false, false                  // flipX, flipY
        );
        for (Laser laser : lasers) {// Desenhar lasers
            loteDesenho.draw(laserTextura, laser.forma.x, laser.forma.y, laser.forma.width, laser.forma.height);
        }
        loteDesenho.end();// Finalizar o lote de desenho
    }

    private void logic() {
        // lógica do jogo, como movimentação de inimigos, colisões, etc.
        //  mover o inimigo em direção ao jogador
        for (Rectangle inimigo : inimigos) {
            if (inimigo.x < jogador.x) {
                inimigo.x += 100 * Gdx.graphics.getDeltaTime(); // Mover para a direita
            } else if (inimigo.x > jogador.x) {
                inimigo.x -= 100 * Gdx.graphics.getDeltaTime(); // Mover para a esquerda
            }
            if (inimigo.y < jogador.y) {
                inimigo.y += 100 * Gdx.graphics.getDeltaTime(); // Mover para cima
            } else if (inimigo.y > jogador.y) {
                inimigo.y -= 100 * Gdx.graphics.getDeltaTime();
            }
        }

        checkCollisions();     // Verificar colisões entre o jogador e os obstáculos

        // Verificar colisões entre o jogador e os inimigos
        for (Rectangle inimigo : inimigos) {
            if (jogador.overlaps(inimigo)) {
                // Parar a música de fundo se estiver a tocar
                if (musicaFundo.isPlaying()) {
                    musicaFundo.pause();
                    somColisao.play();
                }
                // Tocar som de colisão
  //              somColisao.play();
            }
        }
        // Verificar se jogador entrou no portal
        if (jogador.overlaps(portal)) {
            //musicaFundo.stop(); // parar música ao mudar de ecrã (opcional)
            ((Principal) Gdx.app.getApplicationListener()).setScreen(new SecondScreen((int) jogador.x, (int) jogador.y));
        }
            // Atualizar lasers
            for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            laser.atualizar(Gdx.graphics.getDeltaTime());
            // Verificar colisões entre lasers e inimigos
            // Se o laser sair da tela, removê-lo
            for (int j = 0; j < inimigos.size(); j++) {
                if (laser.forma.overlaps(inimigos.get(j))) {
                    lasers.remove(i);
                    inimigos.remove(j);
                    sons.get(0).play(); // Explosão
                    // Aqui falta adicionar uma animação de explosão
                    i--;
                    break;
                }
            }
        }
    }

    private float tempoEntreTiros = 0.3f;
    private float tempoDesdeUltimoTiro = 0f;

    private void input() {
        // Processar entrada do jogador
        float speed = 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) jogador.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) jogador.x += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) jogador.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) jogador.y -= speed;

        // Obter a posição do mouse e converter para coordenadas do mundo
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);

        // Se o mouse estiver pressionado, mover o jogador em direção à posição do mouse
        if (Gdx.input.isTouched()) {
            jogador.x += Math.signum(mousePos.x - jogador.x) * speed;
            jogador.y += Math.signum(mousePos.y - jogador.y) * speed;
        }
        jogador.x = Math.max(0, Math.min(jogador.x, viewport.getWorldWidth() - jogador.width));
        jogador.y = Math.max(0, Math.min(jogador.y, viewport.getWorldHeight() - jogador.height));

        float lspeed = 200 * Gdx.graphics.getDeltaTime();
        tempoDesdeUltimoTiro += Gdx.graphics.getDeltaTime();
        // Movimentação jogador
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) jogador.x -= lspeed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) jogador.x += lspeed;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) jogador.y += lspeed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) jogador.y -= lspeed;

        // Converter posição do rato para coordenadas do jogo
        Vector3 lmousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(lmousePos);
        if (Gdx.input.isTouched()) {
            jogador.x -= lspeed;
            jogador.y -= lspeed;
        }

      // Verificar se o jogador pressionou a tecla de disparo ou clicou com o mouse
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.justTouched())
            && tempoDesdeUltimoTiro > tempoEntreTiros) {
            Rectangle alvo = encontrarInimigoMaisProximo();
            if (alvo != null) {
                Laser novoLaser = new Laser(
                    jogador.x + jogador.width / 2,
                    jogador.y + jogador.height / 2,
                    alvo
                );
                lasers.add(novoLaser);
                sons.get(1).play(); // Som de disparo
                tempoDesdeUltimoTiro = 0;
            }
        }

        // Movimento com rato (se quiser)
        if (Gdx.input.isTouched()) {
            jogador.x += Math.signum(lmousePos.x - jogador.x) * speed;
            jogador.y += Math.signum(lmousePos.y - jogador.y) * speed;
        }
     // Limitar o jogador dentro dos limites da viewport
      jogador.x = Math.max(0, Math.min(jogador.x, viewport.getWorldWidth() - jogador.width));
      jogador.y = Math.max(0, Math.min(jogador.y, viewport.getWorldHeight() - jogador.height));
    }

    // Verificar colisões com obstáculos
    private void checkCollisions() {
        // Verificar colisões entre o jogador e os obstáculos
        boolean houveColisao = false;
        for (Rectangle obstaculo : obstaculos) {
            if (jogador.overlaps(obstaculo)) {
                    somColisao.play();// Tocar som de colisão
                // Calcular as diferenças entre os centros dos retângulos
                float jogadorCenterX = jogador.x + jogador.width / 2;
                float jogadorCenterY = jogador.y + jogador.height / 2;
                float obstaculoCenterX = obstaculo.x + obstaculo.width / 2;
                float obstaculoCenterY = obstaculo.y + obstaculo.height / 2;
                float diffX = jogadorCenterX - obstaculoCenterX;// diferença no eixo X
                float diffY = jogadorCenterY - obstaculoCenterY;// Diferença entre os centros dos retângulos
                float overlapX = (jogador.width / 2 + obstaculo.width / 2) - Math.abs(diffX);// sobreposição no eixo X
                float overlapY = (jogador.height / 2 + obstaculo.height / 2) - Math.abs(diffY);// sobreposição no eixo Y

                // Resolver a colisão no eixo com menos sobreposição
                if (overlapX < overlapY) {
                    // Corrigir no eixo X
                    if (diffX > 0) {
                        jogador.x += overlapX;// empurrar o jogador para a direita
                    } else {
                        jogador.x -= overlapX;// empurrar o jogador para a esquerda
                    }
                } else {
                    // Corrigir no eixo Y
                    if (diffY > 0) {
                        jogador.y += overlapY;// empurrar o jogador para cima
                    } else {
                        jogador.y -= overlapY;// empurrar o jogador para baixo
                    }
                }
                houveColisao = true;
            }
        }
        if (!houveColisao && !musicaFundo.isPlaying()) {
            musicaFundo.play();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
        System.out.println(">> Entrou no resize() com largura: " + width + " e altura: " + height);
        // Limpar o ecrã
        Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        viewport = new StretchViewport(width, height, camera);// Criar uma nova viewport com a nova largura e altura
        viewport.apply();
        camera.setToOrtho(false, width, height);
        camera.update();
        viewport.update(width, height);// Atualizar a viewport com a nova largura e altura
        loteDesenho.setProjectionMatrix(camera.combined);// Atualizar a matriz de projeção do lote de desenho
    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void hide() {    }

    @Override
    public void dispose() {        // Dispose of textures
        jogadorTextura.dispose();
        inimigoTextura.dispose();
        obstaculoTextura.dispose();
        backgroundTexture.dispose();
        portalTextura.dispose();
        loteDesenho.dispose();
    }
}
