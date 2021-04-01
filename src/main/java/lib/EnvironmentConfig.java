package lib;

import lib.config.Configuration;
import lib.config.Context;
import lib.config.UnbxdService;
import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;

public class EnvironmentConfig {


    public  static String configFile;
    private static Configuration configuration;
    private static final String SITE_CONFIG_FILE="siteConfig.yml";

    private static ThreadLocal<Context> context=new ThreadLocal<Context>();

    public static void loadConfig() throws Exception {
        if(configuration==null)
        {
            Yaml yaml = new Yaml();
            String file=Config.getEnvironmentProfile()+".yaml";
            try(InputStream in = ClassLoader.getSystemResourceAsStream(file)) {
                configuration = yaml.loadAs(in, Configuration.class);
            } catch(Exception ex) {
                ex.printStackTrace();



                throw new Exception(" Exception while loading the Yaml File "+file);
            }
        }
    }

    public static String getApplicationUrl()
    {
        return configuration.getApplicationUrl();
    }

    public static String getSiteKey()
    {
        int siteId=context.get().getSiteId();
        return configuration.getSiteById(siteId).getSiteKey();

    }

    public static String getApiKey()
    {
        int siteId=context.get().getSiteId();
        return configuration.getSiteById(siteId).getApiKey();

    }

    public static String getSiteName()
    {
        int siteId=context.get().getSiteId();

        return configuration.getSiteById(siteId).getName();
    }

    public static String getEmail()
    {
        int userId=context.get().getUserId();
        return configuration.getUserById(userId).getEmail();
    }

    public static String getServiceUrl(String service){

        for(UnbxdService unbxdService:configuration.getServices()){

                if(unbxdService.getName().equalsIgnoreCase(service)){
                    return unbxdService.getHost();
                }
        }

        return "";
    }

    public static String getSiteId()
    {
        int siteId=context.get().getSiteId();
        return String.valueOf(configuration.getSiteById(siteId).getSiteId());
    }

    public static String getPassword()
    {
        int userId=context.get().getUserId();
        return configuration.getUserById(userId).getPassword();
    }

    public static void setContext(int userId, int siteId){

        System.out.println("Setting the Context UserId:"+userId+ " , siteId : "+siteId);
        if(context.get()!=null)
            Assert.fail("Context is already Set , cant recreate it");
        Context newContext=getNewContext(userId,siteId);
        context.set(newContext);
    }

    public static void unSetContext(){
        if(context.get() !=null) {
            System.out.print("Removing the Context userId: " + context.get().getUserId() + " , SiteId: " + context.get().getSiteId());
            context.remove();
        }
    }

    private static Context getNewContext(int userId, int siteId){
        Context context=new Context();
        context.setUserId(userId);
        context.setSiteId(siteId);
        return context;
    }
}
