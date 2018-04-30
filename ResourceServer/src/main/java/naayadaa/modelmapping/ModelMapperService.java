package naayadaa.modelmapping;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public interface ModelMapperService {


    <D> D map(Object source, Class<D> destination);

    Object map(Object source, Object destination);
}
