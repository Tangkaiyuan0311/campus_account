package learn.jpa.demo.security;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseAuthority implements Authority{
    private boolean read = false;
    private boolean update = false;
    private boolean delete = false;
}
