//package com.niit.ToDo.services;
//
//
//import com.niit.ToDo.exception.CategoryNotFoundException;
//import com.niit.ToDo.exception.UserNotFoundException;
//import com.niit.ToDo.model.*;
//import com.niit.ToDo.repository.TodoRepository;
//import com.niit.ToDo.service.TodoServiceImpl;
//
//import com.niit.rabbitmq.domain.UserDTO;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
//@ExtendWith(MockitoExtension.class)
//public class TodoServiceTest {
//
//    @Mock
//    private TodoRepository todoRepository;
//
//    @InjectMocks
//    private TodoServiceImpl todoService;
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
//        List<Todo> todos = new ArrayList<>();
//        List<Todo> todos1 = new ArrayList<>();
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
//        todo1 = null;
//        todo2 = null;
//        todo3 = null;
//        image1 = null;
//        guest1 = null;
//    }
//
//    @Test
//    public void saveCategoryTest() throws UserNotFoundException {
//        when(todoRepository.save(any())).thenReturn(user1A);
//        when(todoRepository.findById(user1A.getEmailId())).thenReturn(java.util.Optional.ofNullable(user1B));
//        when(todoRepository.findByEmailId(user1B.getEmailId())).thenReturn(user1B);
//
//        assertEquals(user1A,todoService.saveCategoryToListWithDefaultStatuses(category1A,user1B.getEmailId()));
//        verify(todoRepository,times(1)).save(any());
//
//    }
//
//    @Test
//    public void deleteCategoryTest() throws UserNotFoundException, CategoryNotFoundException {
//        when(todoRepository.save(any())).thenReturn(user1A);
//        when(todoRepository.findById(user1A.getEmailId())).thenReturn(java.util.Optional.ofNullable(user1B));
//        when(todoRepository.findByEmailId(user1B.getEmailId())).thenReturn(user1B);
//
//        assertEquals(user1A,todoService.saveCategoryToListWithDefaultStatuses(category1A,user1B.getEmailId()));
//        verify(todoRepository,times(1)).save(any());
//
//        assertEquals(user1B,todoService.deleteCategoryFromList(user1A.getEmailId(),category1A.getCategoryId()));
//        verify(todoRepository,times(1)).findById(any());
//        verify(todoRepository,times(1)).save(any());
//    }
//
//    @Test
//    public void getAllCategoryTest() throws UserNotFoundException {
//        when(todoRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(user1A));
//        assertEquals(categoriesA,todoService.getAllCategories(user1A.getEmailId()));
//        verify(todoRepository,times(2)).findById(any());
//    }
//
//    @Test
//    public void saveToDoTest() throws UserNotFoundException {
//        when(todoRepository.save(any())).thenReturn(user1);
//        when(todoRepository.findById(user1A.getEmailId())).thenReturn(java.util.Optional.ofNullable(user1A));
//        when(todoRepository.findByEmailId(user1A.getEmailId())).thenReturn(user1A);
//
//        assertEquals(user1,todoService.saveTodoToList(todo1,status1.getStatusId(),category1.getCategoryId(),user1.getEmailId()));
//        verify(todoRepository,times(1)).save(any());
//    }
//
//    @Test
//    public void deleteTodoTest() throws UserNotFoundException {
//        when(todoRepository.save(any())).thenReturn(user1);
//        when(todoRepository.findById(user1A.getEmailId())).thenReturn(java.util.Optional.ofNullable(user1A));
//        when(todoRepository.findByEmailId(user1A.getEmailId())).thenReturn(user1A);
//
//        assertEquals(user1,todoService.saveTodoToList(todo1,status1.getStatusId(),category1.getCategoryId(),user1.getEmailId()));
//        verify(todoRepository,times(1)).save(any());
//
//        assertEquals(user1A,todoService.deleteTodoFromList(todo1.getTodoId(), status1.getStatusId(), category1.getCategoryId(), user1.getEmailId()));
//        verify(todoRepository,times(1)).findById(any());
//        verify(todoRepository,times(1)).save(any());
//    }
//
//    @Test
//    public void getAllTodoTest() throws UserNotFoundException {
//        when(todoRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(user1));
//        assertEquals(categories,todoService.getAllCategories(user1.getEmailId()));
//        verify(todoRepository,times(2)).findById(any());
//    }
//
//    @Test
//    public void updateToDoTest() throws UserNotFoundException {
//        when(todoRepository.save(any())).thenReturn(user1);
//        when(todoRepository.findById(user1A.getEmailId())).thenReturn(java.util.Optional.ofNullable(user1A));
//        when(todoRepository.findByEmailId(user1A.getEmailId())).thenReturn(user1A);
//
//        assertEquals(user1,todoService.updateTodoToList(todo1,status1.getStatusId(),category1.getCategoryId(),user1.getEmailId()));
//    }
//
//
//
//
//}
