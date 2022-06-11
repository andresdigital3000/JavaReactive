package APIStream;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Alumno {
  public String nombre;
  public int edad;
  public List<Materia> materias;

}
