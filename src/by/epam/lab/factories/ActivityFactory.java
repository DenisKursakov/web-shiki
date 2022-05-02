package by.epam.lab.factories;

import by.epam.lab.ifaces.ActivityDAO;
import by.epam.lab.implementations.activityImpls.ActivityImplDB;
import by.epam.lab.implementations.activityImpls.ActivityMemoryImpl;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletConfig;
import java.util.ResourceBundle;
import java.util.function.Function;

public class ActivityFactory {
    enum ActiveType {
        CSV (ActivityMemoryImpl::new),
        DB (ActivityImplDB::new),
        MEMORY(ActivityMemoryImpl::new);
        private final Function<ResourceBundle, ActivityDAO> sourceImpl;

        ActiveType(Function<ResourceBundle, ActivityDAO> sourceImpl) {
            this.sourceImpl = sourceImpl;
        }
    }
    private static ActivityDAO activeImpl;

    public static void init (ResourceBundle rb){
        String factoryActive = rb.getString(ConstantsJSP.FACTORY_ACT_PARAM);
        ActiveType source = ActiveType.valueOf(factoryActive.toUpperCase());
        activeImpl = source.sourceImpl.apply(rb);
    }
    public static ActivityDAO getClassFromFactory () {
        return activeImpl;
    }

}
