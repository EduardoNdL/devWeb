package school.sptech.validation.Dto;

import school.sptech.validation.Entity.Carro;

import java.util.List;

public class CarroMapper {
    public static CarroResponse toResponseDto(Carro model) {
        if (model == null) {
            return null;
        }

        CarroResponse dto = new CarroResponse();
        dto.setId(model.getId());
        dto.setModelo(model.getModelo());
        dto.setMontadora(model.getMontadora());
        dto.setCor(model.getCor());
        dto.setPreco(model.getPreco());
        dto.setEletrico(model.getEletrico());
        dto.setCavalos(model.getCavalos());
        dto.setAnoFabricacao(model.getAnoFabricacao());

        return dto;
    }

    public static Carro toCarModel(CarroRequest request) {
        if (request == null) {
            return null;
        }

        Carro model = new Carro();
        model.setModelo(request.getModelo());
        model.setMontadora(request.getMontadora());
        model.setCor(request.getCor());
        model.setPreco(request.getPreco());
        model.setEletrico(request.getEletrico());
        model.setCavalos(request.getCavalos());
        model.setAnoFabricacao(request.getAnoFabricacao());

        return model;
    }

    public static List<CarroResponse> toResponseDto(List<Carro> models) {
        return models.stream()
                .map(CarroMapper::toResponseDto)
                .toList();
    }

}
