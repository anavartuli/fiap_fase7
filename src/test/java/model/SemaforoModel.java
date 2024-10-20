package model;
import com.google.gson.annotations.Expose;
//import lombok.Data;
//@Data
public class SemaforoModel {

    public void setCdSemaforo(int cdSemaforo) {
        this.cdSemaforo = cdSemaforo;
    }

    public int getCdSemaforo() {
        return cdSemaforo;
    }

    public void setLgLogradouro(String lgLogradouro) {
        this.lgLogradouro = lgLogradouro;
    }

    public void setLgNumero(int lgNumero) {
        this.lgNumero = lgNumero;
    }

    public void setLgCidade(String lgCidade) {
        this.lgCidade = lgCidade;
    }

    public void setLgEstado(String lgEstado) {
        this.lgEstado = lgEstado;
    }

    public void setTmTempoVerde(int tmTempoVerde) {
        this.tmTempoVerde = tmTempoVerde;
    }

    public void setTmTempoAmarelo(int tmTempoAmarelo) {
        this.tmTempoAmarelo = tmTempoAmarelo;
    }

    public void setTmTempoVeremelho(int tmTempoVeremelho) {
        this.tmTempoVeremelho = tmTempoVeremelho;
    }

    @Expose(serialize = false)
    private int cdSemaforo;
    @Expose
    private String lgLogradouro;
    @Expose
    private int lgNumero;
    @Expose
    private String lgCidade;
    @Expose
    private String lgEstado;
    @Expose
    private int tmTempoVerde;
    @Expose
    private int tmTempoAmarelo;
    @Expose
    private int tmTempoVeremelho;
}
