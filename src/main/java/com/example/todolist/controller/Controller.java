package com.example.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.junit.rules.ErrorCollector;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.Document1;
//import com.zx.repository.service.MyRepository;
import com.example.todolist.repository.MyRepository;
//import com.mongodb.internal.operation.ClientBulkWriteOperation.Exceptions;
//import com.google.common.hash.HashCode;

import java.util.List;


import java.util.HashMap;
import java.util.Map;
//import java.util.Optional;

//import tools.jackson.databind.util.ExceptionUtil;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;







@RestController
@CrossOrigin(origins="*")
public class Controller 
{
   @GetMapping("/HomePage")
   public String HomePage() 
   {
         
       return "Home Page Loaded";
       
   }
  @PostMapping(value = "/ack")
  public ResponseEntity<String> PostHome(@RequestBody Map<String,Object> x) 
  {
      try
      {
         System.out.println(x);
          return ResponseEntity.ok("ok");
      }catch(Exception e)
      {
         System.out.print(e);
         return ResponseEntity.status(500).body("error is : "+e);
      }
     
      
  }
  @GetMapping("/AddTask")
  public String LoadAddTask()
  {
     return "AddTask Page Loaded";
  }
   @Autowired
  private MyRepository repository;
 
  @PostMapping("/AddTaskPost")
  public ResponseEntity<String> PostAddTask(@RequestBody  Document1 entity) {
      

     
      try
      {
         repository.save(entity);
         System.out.println(entity.gettdate());
         System.out.println(entity.getdemail());
        
          return ResponseEntity.ok("Stored Data");
      }catch(Exception e)
      {
         System.out.print(e);
         return ResponseEntity.status(500).body("error is : "+e);
      }
  }
  @DeleteMapping("/Completed/Delete/{num}")
  public ResponseEntity<String> CompleteDeletd(@PathVariable int num)
  {
   try
   {
     if(num==0)
     {
        System.out.println(num);
        repository.deleteAll();
     }else
     {
       System.out.println(num);
        repository.deleteByTtasknum(num);
     }
   
   return ResponseEntity.ok("Comleted");
   }catch(Exception e)
   {
      e.printStackTrace();

      return ResponseEntity.status(500).body("Completed");
   }
   
  }
 @DeleteMapping("Edit/Delete/{num}")
 public ResponseEntity<String> EditDelete(@PathVariable int num)
 {
   System.out.println(num);
   try 
   {
      if(num==0)
      {
         repository.deleteAll();
      }else
      {
         repository.deleteByTtasknum(num);
      }
      return ResponseEntity.ok("deleted");
   } catch (Exception e)
   {
      e.getStackTrace();
      return ResponseEntity.status(500).body("Not Deleted");
   }
 }
 @PutMapping("Edit/{num}")
 public ResponseEntity<Map<String,Object>> Edit(@PathVariable int num,@RequestBody Document1 document) 
 {
    Map<String,Object> map=new HashMap<>();
   try
   { 
      System.out.println(num);
       
       Document1 update=repository.findByTtasknum(num);
       if(update==null)
       {
         System.out.println("No any data like that ");
       }else
       {
         update.settdate(document.gettdate());
         update.settname(document.gettname());
         update.setttime(document.getttime());
         update.setttasknum(document.getttaskNum());
         repository.save(update);
         map.put("tdate",update.gettdate());
         map.put("tname",update.gettname());
         map.put("tnum",update.getttaskNum());
         map.put("ttime",update.getttime());
       }
      return ResponseEntity.ok(map);
     
   }
   catch(Exception e)
   {
      e.printStackTrace();
      map.put("error","error happens");
      return ResponseEntity.status(500).body(map);
   }
 }

 
 @GetMapping("/MyAccount/{email}")
 public ResponseEntity<Map<String,Object>> MyAccount(@PathVariable String email) {
   Map<String,Object> map=new HashMap<>();
  
   try
   {
      
      System.out.println(email);
      List<Document1> doc = repository.findByDemail(email);
      if(!doc.isEmpty())
      {

      for(int i=1;i<=doc.size();i++)
      {
         String Task="Task"+i;
         String Num="Num"+i;
         String Date="Date"+i;
         String Time="Time"+i;
         map.put(Task,doc.get(i-1).gettname());
         map.put(Num,doc.get(i-1).getttaskNum());
         map.put(Date,doc.get(i-1).gettdate());
         map.put(Time,doc.get(i-1).getttime());
         map.put("NumberOfListElement",doc.size());
          map.put("status","yes Data");
         
      }
      System.out.println(doc.get(0).gettname());
     return ResponseEntity.ok(map);
   }else
   {
      map.put("status","No Data");
      return ResponseEntity.ok(map);
   }
    

   }catch(Exception e)
   {
       e.getStackTrace();
       map.put("error","error happen");
       return ResponseEntity.status(500).body(map);
   }
     
 }
 

}
