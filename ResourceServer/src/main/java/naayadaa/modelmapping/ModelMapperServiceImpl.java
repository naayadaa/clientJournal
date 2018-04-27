package naayadaa.modelmapping;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@Service
public class ModelMapperServiceImpl implements InitializingBean, ModelMapperService {

    private final static Logger LOG = LogManager.getLogger(ModelMapperServiceImpl.class);

    private Iterable<Converter> converters;
    private Iterable<PropertyMap> mappings;
    private ModelMapper modelMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        modelMapper = new ModelMapper();
        if(converters != null){
            for(Converter converter: converters){
                modelMapper.addConverter(converter);
            }
        }
        if(mappings != null){
            for(PropertyMap propertyMap: mappings){
                modelMapper.addMappings(propertyMap);
            }
        }
    }

    @Override
    public <D> D map(Object source, Class<D> destination) {

        try {
            D mapped = modelMapper.map(source, destination);
            if (LOG.isTraceEnabled()) {
                LOG.trace("Converted " + source + " TO: " + mapped);
            }
            return mapped;
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public Object map(Object source, Object destination) {

        try {
            modelMapper.map(source, destination);
            if (LOG.isTraceEnabled()) {
                LOG.trace("Converted " + source + " TO: " + destination);
            }
            return destination;
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }


    public void setConverters(Iterable<Converter> converters) {
        this.converters = converters;
    }

    public void setMappings(Iterable<PropertyMap> mappings) {
        this.mappings = mappings;
    }
}
