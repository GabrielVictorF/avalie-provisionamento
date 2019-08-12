package sample;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidmanagement.v1.AndroidManagement;
import com.google.api.services.androidmanagement.v1.model.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SampleApp {
    /**
     * The id of the Google Cloud Platform project.
     */
    private static final String PROJECT_ID =
            //TODO ID do Turismo 01 - "lapinworkturismo";
            //TODO ID do Avalie 01 - "avalie01-210021";
            //TODO ID do Avalie 03 - "avalie03-213613";
            //TODO ID do Avalie 04 - "avalie04-221611";
            //TODO ID do Avalie 05 - "avalie05";
            //TODO ID do Avalie 06 - "avalie06";
            //TODO ID do Avalie 07 - "avalie07";
            //TODO ID do Avalie 08 - "avalie08";
            //TODO ID do Avalie 09 - "avalie09";
            "avalie08";

    /**
     * The JSON credential file for the service account.
     */
    private static final String SERVICE_ACCOUNT_CREDENTIAL_FILE =
            //TODO Credencial Turismo - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/lapinworkturismo-f840c4aa2783.json";
            //TODO Credencial Avalie01 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie01-f0210efb1523.json";
            //TODO Credencial Avalie03 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie03-213613-9e1ceb2154e8.json";
            //TODO Credencial Avalie 04 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie04-221611-8cf72f260281.json";
            //TODO Credencial Avalie 05 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie05-e70255ec9d31.json"
            //TODO Credencial Avalie 06 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie06-39154aec0713.json";
            //TODO Credencial Avalie 07 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie07-deb5e80a7a6f.json"
            //TODO Credencial Avalie 08 - "home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie08-dd8c9ce45489.json"
            //TODO Credencial Avalie 09 - home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie09-241218-fd54025c1717.json
            "/home/gabriel/Workspaces/Java/avalie-provisionamento/src/Credential/avalie08-dd8c9ce45489.json";


    /**
     * The id of the policy for the COSU device.
     */
    private static final String POLICY_ID =
            //TODO Nome da Policy AvalieSobral - "PolicyAvalieFortaleza";
            //TODO Nome da Policy AvalieTUrismo - "policy1";
            //TODO Nome da Policy Avalie03 - "PolicyAvalie03";
            //TODO Nome da Policy Avalie04 - "PolicyAvalie04";
            //TODO Nome da Policy Avalie05 - "PolicyAvalie05";
            //TODO Nome da Policy Avalie06 - "PolicyAvalie06";
            //TODO Nome da Policy Avalie07 - "PolicyAvalie07"
            //TODO Nome da Policy Avalie08 - "PolicyAvalie08";
            //TODO Nome da Policy Avalie09 - "PolicyAvalie09";
      "PolicyAvalie08";

    /**
     * The package name of the COSU app.
     */
    private static final String COSU_APP_PACKAGE_NAME_1 =
            "appinventor.ai_Rodrigo_vasquestuf.TotemPrefeituraT4_wide";

    private static final String COSU_APP_PACKAGE_NAME_2 =
            "appinventor.ai_lapin_unifor.totemLapinSobral";

    private static final String COSU_APP_PACKAGE_NAME_3 =
            "br.gov.ce.sme.fortaleza.digaaiescola";

    /**
     * The OAuth scope for the Android Management API.
     */
    private static final String OAUTH_SCOPE =
            "https://www.googleapis.com/auth/androidmanagement";

    /**
     * The name of this app.
     */
    private static final String APP_NAME = "Android Management API sample app";

    /**
     * The Android Management API client.
     */
    private final AndroidManagement androidManagementClient;

    public static void main(String[] args)
            throws IOException, GeneralSecurityException {
        new SampleApp(getAndroidManagementClient()).run();
    }

    public SampleApp(AndroidManagement androidManagementClient) {
        this.androidManagementClient = androidManagementClient;
    }

    /**
     * Runs the app.
     */
    public void run() throws IOException {
        // Create an enterprise. If you've already created an enterprise, the
        // createEnterprise call can be commented out and replaced with your
        // enterprise name.

        //String enterpriseName = createEnterprise();

        String enterpriseName =
        //TODO Empresa do Avalie01 "enterprises/LC040a6zuv";
        //TODO Empresa do Avalie02 "enterprises/LC02rrxupe";
        //TODO Empresa do Avalie03 "enterprises/LC03rzvlai";
        //TODO Empresa do Avalie04 "enterprises/LC01csg5gn";
        //TODO Empresa do Avalie05 "enterprises/LC01i0vabk"
        //TODO Empresa do Turismo01 "enterprises/LC02gpps62";
        //TODO Empresa do Turismo02 "enterprises/LC021wm771";
        //TODO Empresa do Avalie 06 "enterprises/LC03u43jxw";
        //TODO Empresa do Avalie 07 "enterprises/LC02upbxam";
        //TODO Empresa do Avalie 08 "enterprises/LC03sf0185";
        //TODO Empresa do Avalie 09 "enterprises/";
        "enterprises/LC03sf0185";
        System.out.println("Enterprise created with name: " + enterpriseName);

        // Set the policy to be used by the device.
        //TODO CHECAR SET POLICY
        setPolicy(enterpriseName, POLICY_ID, getCosuPolicy());

        /**
         * Deletar Pollicy/Tablet
         * Metodo para deletar - deve ser passado por ex - ""enterprises/LC01i0vabk/devices/31b7e3404274f2e2".
         */
     //androidManagementClient.enterprises().devices().delete("enterprises/LC02gpps62/devices/32c181fc9ad12825").execute();


        // Create an enrollment token to enroll the device.

        String token = createEnrollmentToken(enterpriseName, POLICY_ID);
        System.out.println("Enrollment token (to be typed on device): " + token);

        // List some of the devices for the enterprise. There will be no devices for
        // a newly created enterprise, but you can run the app again with an
        // existing enterprise after enrolling a device.

        List<Device> devices = listDevices(enterpriseName);
        for (Device device : devices) {
//            device.getDeviceSettings().setDevelopmentSettingsEnabled(true);2.660
            System.out.println("------------------------------------------------------------");
            System.out.println("Numero de serie: " + device.getHardwareInfo().getSerialNumber());
            System.out.println("Device Name: " + device.getName());
            System.out.println("Policy Name:  " + device.getAppliedPolicyName() + "\nPolicy Version : " + device.getAppliedPolicyVersion());
            System.out.println("Última sincronização de policy: "+ device.getLastPolicySyncTime());
            System.out.println("Último report: " + device.getLastStatusReportTime());
//            System.out.println(device.getDeviceSettings().getDevelopmentSettingsEnabled());
//            System.out.println("Compliance: " + device.getAppliedState());
            System.out.println("Data de Provisionamento: " + device.getEnrollmentTime());
        }


        // If there are any devices, reboot one.

//  if (devices.isEmpty()) {
//            System.out.println("No devices found.");
//        } else {
//            rebootDevice(devices.get(0));
//        }
    }

    /**
     * Builds an Android Management API client.
     */
    private static AndroidManagement getAndroidManagementClient()
            throws IOException, GeneralSecurityException {
        try (FileInputStream input =
                     new FileInputStream(SERVICE_ACCOUNT_CREDENTIAL_FILE)) {
            GoogleCredential credential =
                    GoogleCredential.fromStream(input)
                            .createScoped(Collections.singleton(OAUTH_SCOPE));
            return new AndroidManagement.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    credential)
                    .setApplicationName(APP_NAME)
                    .build();
        }
    }

    /**
     * Creates a new enterprise. Returns the enterprise name.
     */
    private String createEnterprise() throws IOException {
        // Initiate signup process.
        System.out.println("Creating signup URL...");
        SignupUrl signupUrl =
                androidManagementClient
                        .signupUrls()
                        .create()
                        .setProjectId(PROJECT_ID)
                        .setCallbackUrl("https://localhost:9999")
                        .execute();
        System.out.print(
                "To sign up for a new enterprise, open this URL in your browser: ");
        System.out.println(signupUrl.getUrl());
        System.out.println(
                "After signup, you will see an error page in the browser.");
        System.out.print(
                "Paste the enterpriseToken value from the error page URL here: ");
        String enterpriseToken =
                new BufferedReader(new InputStreamReader(System.in)).readLine();

        // Create the enterprise.
        System.out.println("Creating enterprise...");
        return androidManagementClient
                .enterprises()
                .create(new Enterprise())
                .setProjectId(PROJECT_ID)
                .setSignupUrlName(signupUrl.getName())
                .setEnterpriseToken(enterpriseToken)
                .execute()
                .getName();
    }

    /**
     * Gets a Policy for a COSU device.
     */
    private Policy getCosuPolicy() {
        List<String> categories = new ArrayList<>();
        //Adição das categorias do COSU App
        categories.add("android.intent.category.HOME");
        categories.add("android.intent.category.DEFAULT");
        List<ApplicationPolicy> listaAppPolicys = new ArrayList<>();
        //Adição das aplicações
        listaAppPolicys.add(
                new ApplicationPolicy()
                        .setPackageName("es.acore.android.limitedlauncher")
                        .setInstallType("FORCE_INSTALLED")
                        .setDefaultPermissionPolicy("GRANT")
                        .setLockTaskAllowed(true));
        listaAppPolicys.add(
                new ApplicationPolicy()
                        .setPackageName("com.android.chrome")
                        .setInstallType("FORCE_INSTALLED")
                        .setDefaultPermissionPolicy("GRANT")
                        .setLockTaskAllowed(true));
        listaAppPolicys.add(
                new ApplicationPolicy()
                        .setPackageName("com.asus.filemanager")
                        .setInstallType("FORCE_INSTALLED")
                        .setDefaultPermissionPolicy("GRANT")
                        .setLockTaskAllowed(true));
        listaAppPolicys.add(
                new ApplicationPolicy()
                        .setPackageName(COSU_APP_PACKAGE_NAME_1)
                        .setInstallType("FORCE_INSTALLED")
                        .setDefaultPermissionPolicy("GRANT")
                        .setLockTaskAllowed(true));

        listaAppPolicys.add(
                new ApplicationPolicy()
                        .setPackageName(COSU_APP_PACKAGE_NAME_2)
                        .setInstallType("FORCE_INSTALLED")
                        .setDefaultPermissionPolicy("GRANT")
                        .setLockTaskAllowed(true));

        listaAppPolicys.add(
                new ApplicationPolicy()
                        .setPackageName(COSU_APP_PACKAGE_NAME_3)
                        .setInstallType("FORCE_INSTALLED")
                        .setDefaultPermissionPolicy("GRANT")
                        .setLockTaskAllowed(true));

        return new Policy()
                .setApplications(listaAppPolicys)
                .setPersistentPreferredActivities(
                        Collections.singletonList(
                                new PersistentPreferredActivity()
                                        .setReceiverActivity("es.acore.android.limitedlauncher")
                                        .setActions(
                                                Collections.singletonList("android.intent.action.MAIN"))
                                        .setCategories(categories)))
                .setKeyguardDisabled(true)
                .setStatusBarDisabled(true)
                .setScreenCaptureDisabled(true)
                .setSystemUpdate(
                        new SystemUpdate()
                                .setType("WINDOWED")
                                .setStartMinutes(1439)
                                .setEndMinutes(60))
                .setStatusReportingSettings(
                        new StatusReportingSettings()
                                .setApplicationReportsEnabled(true)
                                .setDeviceSettingsEnabled(true)
                                .setSoftwareInfoEnabled(true)
                                .setMemoryInfoEnabled(true)
                                .setNetworkInfoEnabled(true)
                                .setDisplayInfoEnabled(true)
                                .setPowerManagementEventsEnabled(true)
                                .setHardwareStatusEnabled(true)
                )
                .setMaximumTimeToLock(0L)
                .setInstallUnknownSourcesAllowed(true)
                .setAdjustVolumeDisabled(false)
                .setUninstallAppsDisabled(false)
                .setInstallAppsDisabled(false)
                .setBlockApplicationsEnabled(true);
    }

    /**
     * Sets the policy of the given id to the given value.
     */
    private void setPolicy(String enterpriseName, String policyId, Policy policy)
            throws IOException {
        System.out.println("Setting policy...");
        String name = enterpriseName + "/policies/" + policyId;
        androidManagementClient
                .enterprises()
                .policies()
                .patch(name, policy)
                .execute();
    }

    /**
     * Creates an enrollment token.
     */
    private String createEnrollmentToken(String enterpriseName, String policyId)
            throws IOException {
        System.out.println("Creating enrollment token...");
        EnrollmentToken token =
                new EnrollmentToken().setPolicyName(policyId).setDuration("86400s");
        return androidManagementClient
                .enterprises()
                .enrollmentTokens()
                .create(enterpriseName, token)
                .execute()
                .getValue();
    }

    private ApplicationReport listaApps(Device device) {
        List<ApplicationReport> listaDeApps = device.getApplicationReports();
        for (ApplicationReport applicationReport :
                listaDeApps) {
            if (applicationReport.getPackageName().equals("appinventor.ai_Rodrigo_vasquestuf.TotemPrefeituraT4_wide")) {
                System.out.println(applicationReport.getVersionName());
            }
//            System.out.println(applicationReport.getPackageName() + ":");
        }
        return null;
    }

    /**
     * Lists the first page of devices for an enterprise.
     */
    private List<Device> listDevices(String enterpriseName) throws IOException {
        System.out.println("Listing devices...");
        ListDevicesResponse response =
                androidManagementClient
                        .enterprises()
                        .devices()
                        .list(enterpriseName)
                        .execute();
        return response.getDevices() == null
                ? new ArrayList<>() : response.getDevices();
    }

    /**
     * Reboots a device. Note that reboot only works on Android N+.
     */
    private void rebootDevice(Device device) throws IOException {
        System.out.println(
                "Sending reboot command to " + device.getName() + "...");
        Command command = new Command().setType("REBOOT");
        androidManagementClient
                .enterprises()
                .devices()
                .issueCommand(device.getName(), command)
                .execute();
    }
}