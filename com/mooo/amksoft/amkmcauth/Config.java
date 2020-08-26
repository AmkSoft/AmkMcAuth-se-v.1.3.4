package com.mooo.amksoft.amkmcauth;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.FileUtil;

public class Config
{
  public static boolean disableIfOnlineMode;
  public static boolean requireLogin;
  public static boolean kickIfAlreadyOnline;
  public static boolean KickOnPasswordFail;
  public static boolean useLoginPermission;
  public static String loginPermission;
  public static boolean allowChat;
  public static String chatPrefix;
  public static boolean allowCommands;
  public static List<String> allowedCommands;
  public static boolean allowMovementWalk;
  public static boolean allowMovementLook;
  public static long allowMovementTime;
  public static boolean godMode;
  public static boolean godModeAfterLogin;
  public static long godModeLength;
  public static long removeAfterDays;
  public static boolean remindEnabled;
  public static long remindInterval;
  public static long saveUserdataInterval;
  public static boolean sessionsEnabled;
  public static boolean sessionsCheckIP;
  public static long sessionLength;
  public static String sessionType;
  public static List<String> disallowedPasswords;
  public static String passwordHashType;
  public static boolean validateUsernames;
  public static String usernameRegex;
  public static boolean invisibleMode;  
  public static List<String> playerActionSJoin;
  public static List<String> playerActionSJReg;    
  public static List<String> playerActionSJGrc;
  public static List<String> playerActionLogin;
  public static List<String> playerActionLogof;
  public static List<String> playerActionLeave;
  public static boolean adventureMode;
  public static boolean teleportToSpawn;
  public static String spawnWorld;
  public static boolean useSpawnAt;
  public static long spawnWorldX;
  public static long spawnWorldY;
  public static long spawnWorldZ;
  public static boolean kickPlayers;
  public static long kickAfter;
  public static boolean checkOldUserdata;
  public static long maxUsersPerIpaddress;

  private boolean NewSettng = false;

  private final AmkMcAuth plugin;
  
  public Config(AmkMcAuth instance)
  {
    this.plugin = instance;
    File config = new File(this.plugin.getDataFolder(), "config.yml");
    if (!config.exists())
    {
      if (!config.getParentFile().mkdirs()) {
        this.plugin.getLogger().warning("Could not create config.yml directory.");
      }
      this.plugin.saveDefaultConfig();
    }
    reloadConfiguration();
  }
  
  /**
   * Player configuration manager.
   *
   * @param Configuration-File Name, Config-Element Name,  Default value
   */
  private String GetSetting(FileConfiguration ConfigFile, String ConfigName, String Default) {
  	String RetVal = "";
      if(ConfigFile.isSet(ConfigName)) {
      	RetVal= ConfigFile.getString(ConfigName);
      }
      else
      	{
      	ConfigFile.set(ConfigName, Default);        	
      	NewSettng=true;
      	RetVal = Default;
      	this.plugin.getLogger().info("Config.yml Check: New Setting: " + ConfigName + " added!");
      }
      return RetVal;
  }
  private Long GetSetting(FileConfiguration ConfigFile, String ConfigName, Long Default) {
  	Long RetVal = 0L;
      if(ConfigFile.isSet(ConfigName)) {
      	RetVal= ConfigFile.getLong(ConfigName);
      }
      else
      	{
      	ConfigFile.set(ConfigName, Default);        	
      	NewSettng=true;
      	RetVal = Default;
      	this.plugin.getLogger().info("Config.yml Check: New Setting: " + ConfigName + " added!");
      }
      return RetVal;
  }
  private boolean GetSetting(FileConfiguration ConfigFile, String ConfigName, boolean Default) {
  	boolean RetVal = false;
      if(ConfigFile.isSet(ConfigName)) {
      	RetVal= ConfigFile.getBoolean(ConfigName);
      }
      else
      	{
      	ConfigFile.set(ConfigName, Default);        	
      	NewSettng=true;
      	RetVal = Default;
      	this.plugin.getLogger().info("Config.yml Check: New Setting: " + ConfigName + " added!");
      }
      return RetVal;
  }
  private List<String> GetSetting(FileConfiguration ConfigFile, String ConfigName, List<String> Default) {
  	List<String> RetVal = null;
      if(ConfigFile.isSet(ConfigName)) {
      	RetVal= ConfigFile.getStringList(ConfigName);
      }
      else
      	{
      	ConfigFile.set(ConfigName, Default);        	
      	NewSettng=true;
      	RetVal = Default;
      	this.plugin.getLogger().info("Config.yml Check: New Setting: " + ConfigName + " added!");
      }
      return RetVal;
  }

  
  public void reloadConfiguration()
  {
    this.plugin.reloadConfig();
    FileConfiguration c = this.plugin.getConfig();
    
    //requireLogin = c.getBoolean("login.require");
    requireLogin		= GetSetting(c,"login.require"						,true);
    //disableIfOnlineMode = c.getBoolean("login.disable_if_online_mode");
    disableIfOnlineMode	= GetSetting(c,"login.disable_if_online_mode"		,false);
    //kickIfAlreadyOnline = c.getBoolean("login.kick_if_already_online");
    kickIfAlreadyOnline	= GetSetting(c,"login.kick_if_already_online"		,false);
    
    //KickOnPasswordFail = c.getBoolean("login.kick_on_password_fail");
    KickOnPasswordFail	= GetSetting(c,"login.kick_on_password_fail"		,false);
    
    //useLoginPermission = c.getBoolean("login.permission.enabled");
    useLoginPermission	= GetSetting(c,"login.permission.enabled"			,false);
    //loginPermission = c.getString("login.permission.permission");
    loginPermission		= GetSetting(c,"login.permission.permission"		,"amkauth.requirelogin");

    
    //allowChat = c.getBoolean("login.restrictions.chat.allowed");
    allowChat			= GetSetting(c,"login.restrictions.chat.allowed"	,false);
    //chatPrefix = c.getString("login.restrictions.chat.prefix");
    chatPrefix			= GetSetting(c,"login.restrictions.chat.prefix"		,"[NLI] ");
    
    //allowCommands = c.getBoolean("login.restrictions.commands.allowed");
    allowCommands		= GetSetting(c,"login.restrictions.commands.allowed",false);
    //allowedCommands = c.getStringList("login.restrictions.commands.exempt");
    allowedCommands		= GetSetting(c,"login.restrictions.commands.exempt"	,Arrays.asList("!","we cui"));        
    
    //allowMovementWalk = c.getBoolean("login.restrictions.movement.walk");
    allowMovementWalk	= GetSetting(c,"login.restrictions.movement.walk"	,false);
    //allowMovementLook = c.getBoolean("login.restrictions.movement.look_around");
    allowMovementLook	= GetSetting(c,"login.restrictions.movement.look_around",true);
    //allowMovementTime = c.getLong("login.restrictions.movement.allowmovetime");
    allowMovementTime	= GetSetting(c,"login.restrictions.movement.allowmovetime",0L);
    
    //godMode = c.getBoolean("login.godmode.enabled");
    godMode				= GetSetting(c,"login.godmode.enabled"				,true);
    //godModeAfterLogin = c.getBoolean("login.godmode.after_login.enabled");
    godModeAfterLogin	= GetSetting(c,"login.godmode.after_login.enabled"	,true);
    //godModeLength = c.getLong("login.godmode.after_login.length");
    godModeLength		= GetSetting(c,"login.godmode.after_login.length"	,10L);
    
    //remindEnabled = c.getBoolean("login.remind.enabled");
    remindEnabled		= GetSetting(c,"login.remind.enabled"				,true);
    //remindInterval = c.getLong("login.remind.interval");
    remindInterval		= GetSetting(c,"login.remind.interval"				,10L);
    //kickPlayers = c.getBoolean("login.remind.kick.enabled");
    kickPlayers			= GetSetting(c,"login.remind.kick.enabled"			,false);
    //kickAfter = c.getLong("login.remind.kick.wait");
    kickAfter			= GetSetting(c,"login.remind.kick.wait"				,30L);
    
    //sessionsEnabled = c.getBoolean("sessions.enabled");
    sessionsEnabled		= GetSetting(c,"sessions.enabled"					,true);
    //sessionsCheckIP = c.getBoolean("sessions.check_ip");
    sessionsCheckIP		= GetSetting(c,"sessions.check_ip"					,true);
    //sessionLength = c.getLong("sessions.length");
    sessionLength		= GetSetting(c,"sessions.length"					,15L);
    //sessionType = c.getString("sessions.LoginCommandsMessage");
    sessionType			= GetSetting(c,"sessions.LoginCommandsMessage"		,"Commands");

    playerActionSJoin	= GetSetting(c,"sessions.OnJoin"					,Arrays.asList(""));
    playerActionSJReg	= GetSetting(c,"sessions.OnRgstr"					,Arrays.asList(""));        
    playerActionSJGrc	= GetSetting(c,"sessions.OnGrace"					,Arrays.asList(""));        
    playerActionLogin	= GetSetting(c,"sessions.OnLogin"					,Arrays.asList(""));        
    playerActionLogof	= GetSetting(c,"sessions.OnLogof"					,Arrays.asList(""));        
    playerActionLeave	= GetSetting(c,"sessions.OnExit"					,Arrays.asList(""));        

    //disallowedPasswords = c.getStringList("passwords.disallowed");
    disallowedPasswords	= GetSetting(c,"passwords.disallowed"				,Arrays.asList("password","[password]"));        
    //passwordHashType = c.getString("passwords.hash_type");
    passwordHashType	= GetSetting(c,"passwords.hash_type"				,"AMKAUTH");
    
    // In the config.yml file this can be: `regex: "[\\w]{2,16}"`  or  `regex: '[\w]{2,16}'`
    // They are both correct, using single quote no escaping of the '\' escape character needed.
    //validateUsernames = c.getBoolean("usernames.verify");
    validateUsernames	= GetSetting(c,"usernames.verify"					,true);
    //usernameRegex = c.getString("usernames.regex");
    usernameRegex		= GetSetting(c,"usernames.regex"					,"[\\w]{2,16}");
    
    invisibleMode		= GetSetting(c,"login.invisible_mode"				,false);
    //adventureMode = c.getBoolean("login.adventure_mode");
    adventureMode		= GetSetting(c,"login.adventure_mode"				,false);        
    //teleportToSpawn = c.getBoolean("login.teleport_to_spawn");
    teleportToSpawn		= GetSetting(c,"login.teleport_to_spawn"			,false);

    spawnWorld			= GetSetting(c,"login.tpToSpawnTrue.teleportToSpawnWorld","");
    useSpawnAt			= GetSetting(c,"login.tpToSpawnTrue.useSpawnAtLocation",false);  
    spawnWorldX			= GetSetting(c,"login.tpToSpawnTrue.spawnAtLocationX",0L);
    spawnWorldY			= GetSetting(c,"login.tpToSpawnTrue.spawnAtLocationY",66L);
    spawnWorldZ			= GetSetting(c,"login.tpToSpawnTrue.spawnAtLocationZ",0L);

    //checkOldUserdata = c.getBoolean("saving.check_old_userdata");
    checkOldUserdata	= GetSetting(c,"saving.check_old_userdata"			,true);
    
    //saveUserdataInterval = c.getLong("saving.interval");
    saveUserdataInterval= GetSetting(c,"saving.interval"					,10L);
    
    removeAfterDays 	= GetSetting(c,"saving.remove_inactive_after"		,99L);

    //maxUsersPerIpaddress = c.getLong("general.users_per_ipaddress");
    maxUsersPerIpaddress= GetSetting(c,"general.users_per_ipaddress"		,0L);

    //-- Check for invalid inputs and set to default if invalid --//

    if (remindInterval < 1L)        remindInterval = 30L;
    if (saveUserdataInterval < 1L)  saveUserdataInterval = 5L;
    if (sessionLength < 1L)         sessionLength = 15L;
    if (kickAfter < 0L)             kickAfter = 0L;
    if (godModeLength <= 0L)        godModeLength = 10L;
    if (maxUsersPerIpaddress < 0L)  maxUsersPerIpaddress = 50L;

    if(NewSettng) { // We found new settings in the plugin NOT in the config.yml.
    	Date now = new Date();
    	SimpleDateFormat smdf = new SimpleDateFormat("yyyMMdd-HHmm"); 
    	String BackUp = ".backup." + smdf.format(now);
    	File xo = new File(plugin.getDataFolder(), "config.yml");
    	File xn = new File(plugin.getDataFolder(), "config.yml" + BackUp);
    	FileUtil.copy(xo,xn);
        this.plugin.saveConfig(); // Save new Configuration

    	this.plugin.getLogger().info("configuration backupped and recreated due to missing setting(s) ");
    }

    this.plugin.getLogger().info("Configuration settings loaded/reloaded from config.yml.");

  }
}
