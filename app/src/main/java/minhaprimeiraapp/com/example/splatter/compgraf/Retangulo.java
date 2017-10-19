package minhaprimeiraapp.com.example.splatter.compgraf;

//Classes Utilizadas
import javax.microedition.khronos.opengles.GL10;

public class Retangulo extends Geometria
{
    //Construtor da Classe
    public Retangulo(int largura, int altura)
    {
        float[] vetorVertices = {-largura / 2, - altura / 2,
                -largura / 2, altura / 2,
                largura / 2, -altura / 2,
                largura / 2, altura / 2,};

        setVetorVertices(vetorVertices);
    }
}
