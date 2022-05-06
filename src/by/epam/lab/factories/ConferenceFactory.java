package by.epam.lab.factories;

import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.implementations.confImpls.ConferenceImplDB;
import by.epam.lab.utils.ConstantsJSP;

import java.util.ResourceBundle;
import java.util.function.Function;

public class ConferenceFactory {
    enum ConfType {
        CSV (ConferenceImplMemory::new),
        DB (ConferenceImplDB::new),
        MEMORY(ConferenceImplMemory::new);
        private final Function<ResourceBundle, ConferenceDAO> sourceImpl;

        ConfType(Function<ResourceBundle, ConferenceDAO> sourceImpl) {
            this.sourceImpl = sourceImpl;
        }
    }
    private static ConferenceDAO confsImpl;

    public static void init (ResourceBundle rb) {
        String factoryConf = rb.getString(ConstantsJSP.FACTORY_CONF_PARAM);
        ConfType source = ConfType.valueOf(factoryConf.toUpperCase());
        confsImpl = source.sourceImpl.apply(rb);
    }
    public static ConferenceDAO getClassFromFactory () {
        return confsImpl;
    }
}
