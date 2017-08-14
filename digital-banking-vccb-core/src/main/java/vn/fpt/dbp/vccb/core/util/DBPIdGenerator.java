package vn.fpt.dbp.vccb.core.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.naming.ObjectNameNormalizer;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.IdentifierGeneratorHelper;
import org.hibernate.id.IncrementGenerator;
import org.hibernate.id.IntegralDataTypeHolder;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.internal.CoreLogging;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.mapping.Table;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * @see http://stackoverflow.com/questions/3194721/bypass-generatedvalue-in-hibernate-merge-data-not-in-db
 * @author truongdx
 *
 */
public class DBPIdGenerator implements IdentifierGenerator, Configurable {	
	private static final CoreMessageLogger LOG = CoreLogging.messageLogger( DBPIdGenerator.class );

	private Class returnClass;
	private String sql;

	private IntegralDataTypeHolder previousValueHolder;

	
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		System.out.println("====sql=" + sql);
		if ( sql != null ) {
			initializePreviousValueHolder( session );
		}
		if (object == null) {
			throw new HibernateException(new NullPointerException());
		}

		Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		System.out.println("DBPIdGenerator::id=" + id);
		if (id == null) {
			id = previousValueHolder.makeValueThenIncrement();//super.generate(session, obj);
			System.out.println("DBPIdGenerator::newId=" + id);
			
		}
		return id;

	}

	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		returnClass = type.getReturnedClass();

		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		final ObjectNameNormalizer normalizer =
				(ObjectNameNormalizer) params.get( PersistentIdentifierGenerator.IDENTIFIER_NORMALIZER );

		String sequence = "workflow_model_seq";//params.getProperty("sequence");
		System.out.println("DBPIdGenerator::configure::sequence = " + sequence);
		
		if (sequence != null && sequence.trim().length() > 0) {
			sql = "SELECT "+ sequence +".NEXTVAL FROM DUAL";
		} else {
			String column = params.getProperty( "column" );
			if ( column == null ) {
				column = params.getProperty( PersistentIdentifierGenerator.PK );
			}
			column = normalizer.normalizeIdentifierQuoting( column ).render( jdbcEnvironment.getDialect() );
	
			String tableList = params.getProperty( "tables" );
			if ( tableList == null ) {
				tableList = params.getProperty( PersistentIdentifierGenerator.TABLES );
			}
			String[] tables = StringHelper.split( ", ", tableList );
	
			final String schema = normalizer.toDatabaseIdentifierText(
					params.getProperty( PersistentIdentifierGenerator.SCHEMA )
			);
			final String catalog = normalizer.toDatabaseIdentifierText(
					params.getProperty( PersistentIdentifierGenerator.CATALOG )
			);
	
			StringBuilder buf = new StringBuilder();
			for ( int i = 0; i < tables.length; i++ ) {
				final String tableName = normalizer.toDatabaseIdentifierText( tables[i] );
				if ( tables.length > 1 ) {
					//
					buf.append( "select max(" ).append( column ).append( ") as mx from " );
				}
				buf.append( Table.qualify( catalog, schema, tableName ) );
				if ( i < tables.length - 1 ) {
					buf.append( " union " );
				}
			}
			if ( tables.length > 1 ) {
				buf.insert( 0, "( " ).append( " ) ids_" );
				column = "ids_.mx";
			}
	
			sql = "select max(" + column + ") from " + buf.toString();
		}
	}

	private void initializePreviousValueHolder(SessionImplementor session) {
		previousValueHolder = IdentifierGeneratorHelper.getIntegralDataTypeHolder( returnClass );

		final boolean debugEnabled = LOG.isDebugEnabled();
		if ( debugEnabled ) {
			LOG.debugf( "Fetching initial value: %s", sql );
		}
		System.out.println( "++++Fetching initial value: " + sql);
		try {
			PreparedStatement st = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql );
			try {
				ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st );
				try {
					if ( rs.next() ) {
						previousValueHolder.initialize( rs, 0L ).increment();
					}
					else {
						previousValueHolder.initialize( 1L );
					}
					////sql = null;
					if ( debugEnabled ) {
						LOG.debugf( "First free id: %s", previousValueHolder.makeValue() );
					}
					System.out.println("++++First free id: " +  previousValueHolder.makeValue());
				}
				finally {
					session.getJdbcCoordinator().getResourceRegistry().release( rs, st );
				}
			}
			finally {
				session.getJdbcCoordinator().getResourceRegistry().release( st );
				session.getJdbcCoordinator().afterStatementExecution();
			}
		}
		catch (SQLException sqle) {
			throw session.getFactory().getSQLExceptionHelper().convert(
					sqle,
					"could not fetch initial value for increment generator",
					sql
			);
		}
	}

}
