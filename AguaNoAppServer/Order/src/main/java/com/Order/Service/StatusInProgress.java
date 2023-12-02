package com.Order.Service;

//import com.philips.models.WorkflowDataModel;
//@Service
public class StatusInProgress {
   /* private static Map<String, String> storeData = new HashMap<String, String>();

    @Override
    public void triggerEvent(Order eventData) {
        System.out.println("Display To Angular App");
        try {

            System.out.println(eventData.getIdOrder());
            ObjectMapper mapper = new ObjectMapper();
            String jsonText = mapper.writeValueAsString(eventData);
            putPatient(jsonText);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @CachePut(value = "myData")
    public void putMyData(String myData) {
        if (storeData.containsKey("mydata")) {
            storeData.remove("mydata");
        }

        storeData.put("mydata", patientData);
    }

    @Cacheable(value = "myData")
    public String getMyData() {
        String myitem= "";
        try {
            myitem = storeData.get("mydata");
        } catch (Exception e) {
        }

        return myitem;
    }*/
}
