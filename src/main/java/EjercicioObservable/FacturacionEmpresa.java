package EjercicioObservable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacturacionEmpresa {
  private String empresa;
  private Double ventaMensual;

}
