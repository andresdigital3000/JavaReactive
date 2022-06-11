package EjercicioObservable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;


public class Main {
  public static final String CLIENTE_D1 = "D1";
  public static final String CLIENTE_KOAJ = "KOAJ";
  public static final String CLIENTE_CARULLA = "CARULLA";

  static Observable<Integer> observableVentas;
  public static void main (String ... args) {
    //Creamos una lista de las facturas de todos los clientes
    List<FacturacionEmpresa> facturacionEmpresaList = getVentaSemanalAllClients();

    //Creamos observables de la lista con el fin de emitir la facturación
    //imprimir
    System.out.println("----------------------------------");
    System.out.println("CLIENTES ACTUALES... ");
    Observable.fromIterable(facturacionEmpresaList).groupBy(factura -> factura.getEmpresa()).flatMapSingle(Observable::toList).subscribe(s -> System.out.println(s));
    System.out.println("----------------------------------");
    System.out.println("PROMEDIO DE VENTAS X CLIENTE... ");
    Observable.fromIterable(facturacionEmpresaList).groupBy(factura -> factura.getEmpresa())
        .flatMap(groupedO -> groupedO.buffer(10) // [2]
                                     .map(list -> {
                                       double avg = (double) list.stream().mapToDouble(m -> m.getVentaMensual())
                                                           .average().orElse(0);
                                       return new ResultadoAverage(list.get(0).getEmpresa(), avg); // [3]
                                     }))
              .subscribe(s -> System.out.println(s));
  }

  public static List<FacturacionEmpresa> getVentaSemanalAllClients(){
    ArrayList<FacturacionEmpresa> facturacionEmpresaArrayList = new ArrayList<>();
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_D1, 90D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_D1, 30D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_D1, 95D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_D1, 70D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_KOAJ, 90D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_KOAJ, 59D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_KOAJ, 90D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_KOAJ, 906D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_CARULLA, 40D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_CARULLA, 20D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_CARULLA, 450D));
    facturacionEmpresaArrayList.add(getVentaSemanalByClient(CLIENTE_CARULLA, 190D));
    return  facturacionEmpresaArrayList;
  }


  public static FacturacionEmpresa getVentaSemanalByClient(String key, Double valorVentaSemanal){
    return FacturacionEmpresa.builder().empresa(key).ventaMensual(valorVentaSemanal).build();
  }

}
