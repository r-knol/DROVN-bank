package nl.hva.miw.internetbanking.data.validator;

import nl.hva.miw.internetbanking.model.Sector;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SectorValidator implements ConstraintValidator<SectorConstraint, Sector> {

    @Override
    public void initialize(SectorConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Sector sectorField,
                           ConstraintValidatorContext constraintValidatorContext) {
        Sector[] sectors = Sector.values();
        List<Sector> sectorList = Arrays.asList(sectors);
        // if(sectorList.contains(sectorField)) {
        // TODO: Implement this way and delete Sector.NOT_SPECIFIED
        if (sectorList.contains(sectorField) && !sectorField
                .equals(Sector.NOT_SPECIFIED)) { // For demo-purposes
            return true;
        }
        return false;
    }

}
