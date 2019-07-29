package com.app.cdac.acts.mrcomforty.services;

import java.util.*;

import com.app.cdac.acts.mrcomforty.pojos.*;

public interface AdminService {
//Integer registerAdmin(String name,String username,String password);
Admin loginAdmin(String username, String password);
Integer addLocation(String locationname,List<String> services);
boolean deleteLocation(String locationname);
Integer addService(String servicename,List<String> locations);
//String deleteService(String servicename);
}
