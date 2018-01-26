package com.delta.rm.offer.api.services.flight;

import java.util.Iterator;
import java.util.Properties;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class EnvironmentConfig
  extends AbstractConfiguration
{
  public static final String CROWD_WSDL_LOCATION = "crowd.wsdl.location";
  public static final String CROWD_APPLICATION_NAME = "crowd.application.name";
  public static final String CROWD_APPLICATION_PASSWORD = "crowd.application.password";
  public static final String CROWD_MAX_RETRIES = "crowd.max.retries";
  public static final String DATABASE_DEFAULT_SCHEMA = "database.default.schema";
  public static final String DATABASE_SHOW_SQL = "database.show.sql";
  public static final String DATABASE_DIALECT = "database.dialect";
  public static final String DATABASE_URL = "database.url";
  public static final String DATABASE_DRIVER = "database.driver";
  public static final String DATABASE_USERNAME = "database.username";
  public static final String DATABASE_PASSWORD = "database.password";
  public static final String DATABASE_DATA_SOURCE = "database.datasource";
  public static final String DATABASE_TYPE = "database.type";
  public static final String RESOURCEBUNDLE_NAME = "resource.bundle.name";
  public static final String RESOURCEBUNDLE_DOMAIN = "resource.bundle.domain";
  public static final String RESOURCEBUNDLE_TYPE = "resource.bundle.type";
  private static EnvironmentConfig instance;
  private Configuration configuration;
  
  public static EnvironmentConfig getInstance()
  {
    if (instance == null) {
      throw new RuntimeException("Enviornment configuration has not been initialized");
    }
    return instance;
  }
  
  public static boolean isInitialized()
  {
    return instance != null;
  }
  
  public static synchronized EnvironmentConfig loadEnvironmentConfigFromFile(String file)
  {
    if (instance != null) {
      throw new RuntimeException("Enviornment configuration already loaded");
    }
    try
    {
      PropertiesConfiguration configuration = new PropertiesConfiguration(file);
      instance = new EnvironmentConfig(configuration);
      return instance;
    }
    catch (ConfigurationException e)
    {
      throw new RuntimeException("Enviornment configuration could not be loaded from file [" + file + "]", e);
    }
  }
  
  public static synchronized EnvironmentConfig loadEnvironmentConfig(Properties properties)
  {
    if (instance != null) {
      throw new RuntimeException("Enviornment configuration already loaded");
    }
    instance = new EnvironmentConfig(ConfigurationConverter.getConfiguration(properties));
    return instance;
  }
  
  public static synchronized EnvironmentConfig loadEnvironmentConfig(Configuration configuration)
  {
    if (instance != null) {
      throw new RuntimeException("Enviornment configuration already loaded");
    }
    if (configuration == null) {
      configuration = new PropertiesConfiguration();
    }
    instance = new EnvironmentConfig(configuration);
    return instance;
  }
  
  public static synchronized void unloadEnvironmentConfig()
  {
    instance = null;
  }
  
  private EnvironmentConfig(Configuration configuration)
  {
    this.configuration = configuration;
  }
  
  public boolean isEmpty()
  {
    return this.configuration.isEmpty();
  }
  
  public boolean containsKey(String key)
  {
    return this.configuration.containsKey(key);
  }
  
  public Object getProperty(String key)
  {
    return this.configuration.getProperty(key);
  }
  
  public Object getCountryProperty(String country, String key)
  {
    if (country == null) {
      return this.configuration.getProperty(key);
    }
    if (containsKey(key + "." + country.toUpperCase())) {
      return this.configuration.getProperty(key + "." + country.toUpperCase());
    }
    if (containsKey(key + ".default")) {
      return this.configuration.getProperty(key + ".default");
    }
    return this.configuration.getProperty(key);
  }
  
  public Iterator<String> getKeys()
  {
    return this.configuration.getKeys();
  }
  
  protected void addPropertyDirect(String key, Object value)
  {
    this.configuration.setProperty(key, value);
  }
  
  public String getCrowdWsdlLocation()
  {
    return this.configuration.getString("crowd.wsdl.location");
  }
  
  public String getCrowdApplicationName()
  {
    return this.configuration.getString("crowd.application.name");
  }
  
  public String getCrowdApplicationPassword()
  {
    return this.configuration.getString("crowd.application.password");
  }
  
  public int getCrowdMaxRetries()
  {
    return this.configuration.getInt("crowd.max.retries", 5);
  }
  
  public String getDatabaseDefaultSchema()
  {
    return this.configuration.getString("database.default.schema");
  }
  
  public String getDatabaseType()
  {
    return this.configuration.getString("database.type", "oracle");
  }
  
  public String getResourceBundleName()
  {
    return this.configuration.getString("resource.bundle.name");
  }
  
  public String getResourceBundleDomain()
  {
    return this.configuration.getString("resource.bundle.domain");
  }
  
  public String getResourceBundleType()
  {
    return this.configuration.getString("resource.bundle.type");
  }
  
  public Boolean getDatabaseShowSql()
  {
    return Boolean.valueOf(this.configuration.getBoolean("database.show.sql", false));
  }
  
  public String getDatabaseDialect()
  {
    return this.configuration.getString("database.dialect", "org.hibernate.dialect.OracleDialect");
  }
  
  public String getDatabaseDriver()
  {
    return this.configuration.getString("database.driver", "oracle.jdbc.driver.OracleDriver");
  }
  
  public String getDatabaseUrl()
  {
    return this.configuration.getString("database.url");
  }
  
  public String getDatabaseUsername()
  {
    return this.configuration.getString("database.username");
  }
  
  public String getDatabasePassword()
  {
    return this.configuration.getString("database.password");
  }
  
  public String getDatabaseDatasource()
  {
    return this.configuration.getString("database.datasource");
  }
}
