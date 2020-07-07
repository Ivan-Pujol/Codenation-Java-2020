package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estacionamento {
    private int parkingMax = 10;
    private int driverAge = 18;
    private int licenseInfractions = 20;
    List<Carro> parked = new ArrayList<>();

    public void estacionar(Carro carro) {

        if (Objects.isNull(carro.getPlaca())){
            nullPointer("Car has no license-plate.");
        }
        if (Objects.isNull(carro.getCor())){
            nullPointer("Car with no color.");
        }
        if (Objects.isNull(carro.getMotorista())){
            excessao("A Car needs a driver. No autonomous car is accepted.");
        }

        if (carro.getMotorista().getIdade() > 0 && carro.getMotorista().getIdade() < driverAge){
            excessao("Driver has no license-driver.");
        }
        if (carro.getMotorista().getIdade() < 0){
            System.out.println("erro a idade do motorista é: "+ carro.getMotorista().getIdade());
            illegalArg("Negative age can't be accepted.");
        }
        if (carro.getMotorista().getPontos()< 0){
            illegalArg("Negative Age is not acceptable.");
        }
        if (carro.getMotorista().getPontos()>licenseInfractions){
            excessao("Driver has more than 20 points.");
        }

        if (parked.size()<parkingMax){
            parked.add(carro);
        }else if (makeSpace()){
            parked.add(carro);
        }else {
            excessao("No parking lot available.");
        }

      
    }

    public int carrosEstacionados() {
        return parked.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return parked.contains(carro);
    }

    public void excessao(String msg){
        throw new EstacionamentoException(msg);
    }
    public void nullPointer(String msg){
        throw new NullPointerException(msg);
    }
    public void illegalArg(String msg){
        throw new IllegalArgumentException(msg);
    }

    public boolean makeSpace(){
        for (Carro car : parked) {
            if(car.getMotorista().getIdade() <= 55){
                parked.remove(car);
                return true;
            }
        }
        return false;
    }
}
