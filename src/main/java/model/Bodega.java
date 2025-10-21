// Clase modelo Bodega
package model;

public class Bodega {
    private int idBodega;
    private String nombreBodega;
    private String ubicacionBodega;
    private String descripcionBodega;
    private String telefonoBodega;
    private String capacidadBodega;
    private int mpioId;
    private int deptoId;

    public Bodega() {}

    public Bodega(int idBodega, String nombreBodega, String ubicacionBodega,
                  String descripcionBodega, String telefonoBodega, String capacidadBodega,
                  int mpioId, int deptoId) {
        this.idBodega = idBodega;
        this.nombreBodega = nombreBodega;
        this.ubicacionBodega = ubicacionBodega;
        this.descripcionBodega = descripcionBodega;
        this.telefonoBodega = telefonoBodega;
        this.capacidadBodega = capacidadBodega;
        this.mpioId = mpioId;
        this.deptoId = deptoId;
    }

    // Getters y Setters
    public int getIdBodega() { return idBodega; }
    public void setIdBodega(int idBodega) { this.idBodega = idBodega; }

    public String getNombreBodega() { return nombreBodega; }
    public void setNombreBodega(String nombreBodega) { this.nombreBodega = nombreBodega; }

    public String getUbicacionBodega() { return ubicacionBodega; }
    public void setUbicacionBodega(String ubicacionBodega) { this.ubicacionBodega = ubicacionBodega; }

    public String getDescripcionBodega() { return descripcionBodega; }
    public void setDescripcionBodega(String descripcionBodega) { this.descripcionBodega = descripcionBodega; }

    public String getTelefonoBodega() { return telefonoBodega; }
    public void setTelefonoBodega(String telefonoBodega) { this.telefonoBodega = telefonoBodega; }

    public String getCapacidadBodega() { return capacidadBodega; }
    public void setCapacidadBodega(String capacidadBodega) { this.capacidadBodega = capacidadBodega; }

    public int getMpioId() { return mpioId; }
    public void setMpioId(int mpioId) { this.mpioId = mpioId; }

    public int getDeptoId() { return deptoId; }
    public void setDeptoId(int deptoId) { this.deptoId = deptoId; }

    @Override
    public String toString() {
        return "Bodega{" +
                "idBodega=" + idBodega +
                ", nombreBodega='" + nombreBodega + '\'' +
                ", ubicacionBodega='" + ubicacionBodega + '\'' +
                ", descripcionBodega='" + descripcionBodega + '\'' +
                ", mpioId=" + mpioId +
                ", deptoId=" + deptoId +
                '}';
    }
}