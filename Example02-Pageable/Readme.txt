http://localhost:8080/books/v1?size=2&page=0
http://localhost:8080/books/v2?size=2&page=0
http://localhost:8080/books/v3?size=2&page=0
http://localhost:8080/books/v3?size=2&page=0&sort=id

First, run insert sql commands on h2-console. Then send page request.
http://localhost:8080/h2-console

Not: We can use both of them. JpaRepository more comprehensive
public interface BookRepository extends PagingAndSortingRepository {
public interface BookRepository extends JpaRepository<Book, Long> {
