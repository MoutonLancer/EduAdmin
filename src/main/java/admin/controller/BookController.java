//package admin.controller;
//
//
//
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.awt.print.Book;
//import java.util.List;
//
//@RestController
//@ResponseBody
//@RequestMapping("/books")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//    @GetMapping
//    public R getAll(){
//        R r = new R();
//        List list = bookService.getAll();
//        r.setFlag(!list.isEmpty());
//        r.setData(list);
//        return r;
//    }
//
//    @GetMapping("{id}")
//    public R getByID(@PathVariable int id){
//        R r = new R();
//        Book book = bookService.getById(id);
//        r.setFlag(book != null);
//        r.setData(book);
//        return r;
//    }
//
//    @GetMapping("_{id}/_{name}/_{type}")
//    public R getByInfo(@PathVariable String id,@PathVariable String name, @PathVariable String type) throws Exception {
//        if(name.equals("wjp"))
//            throw new Exception();
//        R r = new R();
//        List<Book> books = bookService.getByInfo(id, name, type);
//        r.setFlag(!books.isEmpty());
//        r.setData(books);
//        return r;
//    }
//
//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
//        R r = new R();
//        Page<Book> page = bookService.getPage(currentPage, pageSize);
//        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
//            page = bookService.getPage((int)page.getPages(), pageSize);
//        r.setFlag(!page.getRecords().isEmpty());
//        r.setData(page);
//
//  System.out.println("--------------hot---------------");
//  System.out.println("--------------hot---------------");
//  System.out.println("--------------hot---------------");
//
//        return r;
//    }
//
//
//
//
//
//    @PostMapping
//    public R Save(@RequestBody Book book){
//        R r = new R();
//        r.setFlag(bookService.save(book));
//        return r;
//    }
//
//    @DeleteMapping("{id}")
//    public R Delete(@PathVariable int id){
//        R r = new R();
//        r.setFlag(bookService.removeById(id));
//        return r;
//    }
//
//    @PutMapping
//    public R update(@RequestBody Book book){
//        R r = new R();
//        r.setFlag(bookService.update(book,new UpdateWrapper<Book>().eq("id",book.getId())));
//        return r;
//    }
//}
