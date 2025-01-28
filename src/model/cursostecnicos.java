package model;

public class cursostecnicos {
    int idcursostecnicos; 
    String cursostecnicos;

    public int getIdcursostecnicos() {
        return idcursostecnicos;
    }

    public void setIdcursostecnicos(int idcursostecnicos) {
        this.idcursostecnicos = idcursostecnicos;
    }

    public String getCursostecnicos() {
        return cursostecnicos;
    }

    public void setCursostecnicos(String cursostecnicos) {
        this.cursostecnicos = cursostecnicos;
    }

    public cursostecnicos(int idcursostecnicos, String cursostecnicos) {
        this.idcursostecnicos = idcursostecnicos;
        this.cursostecnicos = cursostecnicos;
    }
    
     public cursostecnicos() {
   }
   
   @Override
   public String toString() {
      return cursostecnicos ;
   }  
}
