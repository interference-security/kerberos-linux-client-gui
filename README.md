# kerberos-linux-client-gui
Kerberos Linux Client GUI based on Java

##Run the JAR file 
java -jar kerberos-linux-client-gui.jar

##Important:
Security at your own risk. Create issues or pull requests to contribute.

##Install requirements:
apt-get install libpam-ldapd libnss-ldapd libsasl2-modules-gssapi-mit krb5-user

##Sample configuration during installation of above requirements:
LDAP URI: ldap://myserv.acme.local/

dc=ACME,dc=LOCAL

Default realm: ACME.LOCAL

##Command Line usage:
How to get ticket:

  kinit your_username
  
  Press [Enter] and give your password

How to list ticket:

  klist

How to destroy ticket:

  kdestroy

