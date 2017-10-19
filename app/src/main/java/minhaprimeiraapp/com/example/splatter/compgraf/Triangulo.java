package minhaprimeiraapp.com.example.splatter.compgraf;

import javax.microedition.khronos.opengles.GL10;


public class Triangulo extends Geometria{

    //Construtor da Classe
    public Triangulo(int largura, int altura)
    {
        //CRIA UM VETOR PARA PASSAR AO METODO QUE VAI CRIAR O BUFFER DAS COORDENADAS
        float[] vetorVertices =
                {-largura/2, -altura/2,
                largura/2, -altura/2,
                0, altura/2};

        setVetorVertices(vetorVertices);

    }
}
