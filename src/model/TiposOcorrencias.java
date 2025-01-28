package model;

public class TiposOcorrencias {
   int idTipoOcorrencia;
   String TipoOcorrencia;

   public TiposOcorrencias() {
   }
   
   @Override
   public String toString() {
      return TipoOcorrencia ;
   }

   public TiposOcorrencias(int idTipoOcorrencia, String TipoOcorrencia) {
      this.idTipoOcorrencia = idTipoOcorrencia;
      this.TipoOcorrencia = TipoOcorrencia;
   }

   public int getIdTipoOcorrencia() {
      return idTipoOcorrencia;
   }

   public void setIdTipoOcorrencia(int idTipoOcorrencia) {
      this.idTipoOcorrencia = idTipoOcorrencia;
   }

   public String getTipoOcorrencia() {
      return TipoOcorrencia;
   }

   public void setTipoOcorrencia(String TipoOcorrencia) {
      this.TipoOcorrencia = TipoOcorrencia;
   }
}
