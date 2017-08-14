package vn.fpt.util.json;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;

//see: https://github.com/FasterXML/jackson-databind/issues/266
public class NoDeduplicateIdDeserializationContext extends DefaultDeserializationContext {
	
	
	@Override
	public ReadableObjectId findObjectId(Object id, ObjectIdGenerator<?> gen, ObjectIdResolver resolverType) {
		System.out.println("*****id="+id + "; gen="+gen);
		if (id == null) return null;
		
		 /* 02-Apr-2015, tatu: As per [databind#742] should allow 'null', similar to how
         *   missing id already works.
         */
        if (id == null) {
            return null;
        }

        final ObjectIdGenerator.IdKey key = gen.key(id);
        /*
        if (_objectIds == null) {
            _objectIds = new LinkedHashMap<ObjectIdGenerator.IdKey,ReadableObjectId>();
        } else {
            ReadableObjectId entry = _objectIds.get(key);
            if (entry != null) {
                return entry;
            }
        }
        */

        // Not seen yet, must create entry and configure resolver.
        ObjectIdResolver resolver = null;
        /*
        if (_objectIdResolvers == null) {
            _objectIdResolvers = new ArrayList<ObjectIdResolver>(8);
        } else {
            for (ObjectIdResolver res : _objectIdResolvers) {
                if (res.canUseFor(resolverType)) {
                    resolver = res;
                    break;
                }
            }
        }
        */

        if (resolver == null) {
            resolver = resolverType.newForDeserialization(this);
            //_objectIdResolvers.add(resolver);
        }

        ReadableObjectId entry = createReadableObjectId(key);
        entry.setResolver(resolver);
        //_objectIds.put(key, entry);
        System.out.println(entry);
        return entry;		
	}

	/**
     * Default constructor for a blueprint object, which will use the standard
     * {@link DeserializerCache}, given factory.
     */
    public NoDeduplicateIdDeserializationContext(DeserializerFactory df) {
        super(df, null);
    }

    protected NoDeduplicateIdDeserializationContext(NoDeduplicateIdDeserializationContext src,
            DeserializationConfig config, JsonParser jp, InjectableValues values) {
        super(src, config, jp, values);
    }

    protected NoDeduplicateIdDeserializationContext(NoDeduplicateIdDeserializationContext src) { super(src); }
    
    protected NoDeduplicateIdDeserializationContext(NoDeduplicateIdDeserializationContext src, DeserializerFactory factory) {
        super(src, factory);
    }

    @Override
    public DefaultDeserializationContext copy() {
        //ClassUtil.verifyMustOverride(NoDeduplicateIdDeserializationContext.class, this, "copy");
       return new NoDeduplicateIdDeserializationContext(this);
    }
    
    @Override
    public DefaultDeserializationContext createInstance(DeserializationConfig config,
            JsonParser p, InjectableValues values) {
        return new NoDeduplicateIdDeserializationContext(this, config, p, values);
    }

    @Override
    public DefaultDeserializationContext with(DeserializerFactory factory) {
        return new NoDeduplicateIdDeserializationContext(this, factory);
    }   

}
