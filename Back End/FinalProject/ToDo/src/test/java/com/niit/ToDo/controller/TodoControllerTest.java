//package com.niit.ToDo.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.niit.ToDo.model.*;
//import com.niit.ToDo.service.TodoService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class TodoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private TodoService todoService;
//
//    private User user1;
//    private User user1A;
//    private User user1B;
//    private Category category1;
//    private Category category1A;
//    private Status status1;
//    private Status status1A;
//    private Status status2;
//    private Status status2A;
//    private Todo todo1;
//    private Todo todo2;
//    private Todo todo3;
//    private Image image1;
//    private Guest guest1;
//    List<Category> categoriesA = new ArrayList<>();
//    List<Category> categories = new ArrayList<>();
//    List<Status> statusesA = new ArrayList<>();
//    List<Status> statuses = new ArrayList<>();
//    List<Guest> guests = new ArrayList<>();
//    List<Todo> todos = new ArrayList<>();
//    List<Todo> todos1 = new ArrayList<>();
//
//    @InjectMocks
//    private TodoController todoController;
//
//    @BeforeEach
//    public void setUp() {
//
//        guest1 = new Guest(111,"guest@gmail.com");
//
//        guests.add(guest1);
//        image1 = new Image(111,"www.image.com", "Demo image");
//        todo1= new Todo(1,"6 am to 7 am","Do study", image1, LocalDate.parse("2020-12-20"),true, true, guests);
//        todo2= new Todo(2,"7 am to 8 am","Do something", null, LocalDate.parse("2020-11-17"),false, true, guests);
//        todo3= new Todo(3,"8 am to 9 am","Do anything", image1, LocalDate.parse("2020-05-17"),false, false, null);
//        todos.add(todo1);
//        todos.add(todo2);
//        todos1.add(todo3);
//        status1 = new Status(2,"in progress", todos);
//        status1A = new Status(2,"in progress", null);
//        status2 = new Status(3,"completed", todos1);
//        status2A = new Status(3,"completed", null);
//
//
//        statuses.add(status1);
//        statusesA.add(status1A);
//        statuses.add(status2);
//        statusesA.add(status2A);
//        category1 = new Category(151,"Home",statuses);
//        category1A = new Category(151,"Home",statusesA);
//        categories.add(category1);
//        categoriesA.add(category1A);
//        user1 = new User("tejas@gmail.com","tejas", "tejas", "9988789545",categories);
//        user1A = new User("tejas@gmail.com","tejas", "tejas", "9988789545",categoriesA);
//        user1B = new User("tejas@gmail.com","tejas", "tejas", "9988789545",null);
//        mockMvc= MockMvcBuilders.standaloneSetup(todoController).build();
//
//    }
//
//    @AfterEach
//    public void tearDown(){
//        user1 = null;
//        user1A = null;
//        category1 = null;
//        category1A = null;
//        status1 = null;
//        status1A = null;
//        status2 = null;
//        status2A = null;
//        mockMvc=null;
//        todo1 = null;
//        todo2 = null;
//        todo3 = null;
//        todos = null;
//        image1 = null;
//        guest1 = null;
//    }
//
//    private String jsontoString(final Object obj) {
//        String result;
//        try {
//            ObjectMapper objectMapper=new ObjectMapper();
//            String jsonContent = objectMapper.writeValueAsString(obj);
//            result = jsonContent;
//        }
//        catch (JsonProcessingException ex){
//            result="error while converting to string";
//        }
//        return result;
//    }
//
//    @Test
//    public void saveCategoryTest() throws Exception {
//        when(todoService.saveCategoryToListWithDefaultStatuses(any(),any())).thenReturn(user1A);
//        mockMvc.perform(post("/api/v1/user/category/tejasd8418@gmail.com")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsontoString(user1A)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).saveCategoryToListWithDefaultStatuses(any(),any());
//    }
//
//    @Test
//    public void deleteCategoryTest() throws Exception {
//        when(todoService.deleteCategoryFromList(any(),anyInt())).thenReturn(user1A);
//        mockMvc.perform(delete("/api/v1/user/tejasd8418@gmail.com/151")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).deleteCategoryFromList(any(),anyInt());
//    }
//
//    @Test
//    public void getAllCategoryTest() throws Exception {
//        when(todoService.getAllCategories(any())).thenReturn(categories);
//        mockMvc.perform(get("/api/v1/user/categories/tejas@gmail.com")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).getAllCategories(any());
//
//    }
//
//    @Test
//    public void saveTodoTest() throws Exception {
//        when(todoService.saveTodoToList(any(),anyInt(),anyInt(),any())).thenReturn(user1);
//        mockMvc.perform(post("/api/v1/user/todo/tejas@gmail.com/250/8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsontoString(user1)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).saveTodoToList(any(),anyInt(),anyInt(),any());
//    }
//
//    @Test
//    public void deleteTodoTest() throws Exception {
//        when(todoService.deleteTodoFromList(anyInt(),anyInt(),anyInt(),any())).thenReturn(user1A);
//        mockMvc.perform(delete("/api/v1/user/deletetodo/tejas@gmail.com/151/15/12")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).deleteTodoFromList(anyInt(),anyInt(),anyInt(),any());
//    }
//
//    @Test
//    public void getAllTodoTest() throws Exception {
//        when(todoService.getAllTodo(anyInt(),anyInt(),any())).thenReturn(todos);
//        mockMvc.perform(get("/api/v1/user/todos/tejas@gmail.com/120/3")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).getAllTodo(anyInt(),anyInt(),any());
//
//    }
//
//    @Test
//    public void updateTodoTest() throws Exception {
//        when(todoService.updateTodoToList(any(),anyInt(),anyInt(),any())).thenReturn(user1);
//        mockMvc.perform(post("/api/v1/user/updatetodo/tejas@gmail.com/151/2")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsontoString(user1)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(todoService,times(1)).updateTodoToList(any(),anyInt(),anyInt(),any());
//
//    }
//}
