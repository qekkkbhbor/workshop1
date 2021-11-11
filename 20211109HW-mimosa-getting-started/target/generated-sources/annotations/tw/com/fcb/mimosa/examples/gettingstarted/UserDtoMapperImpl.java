package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tw.com.fcb.mimosa.examples.gettingstarted.User.UserBuilder;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto.UserDtoBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserDto from(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.userName( user.getName() );

        return userDto.build();
    }

    @Override
    public User createUser(CreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( dto.getName() );
        user.age( dto.getAge() );

        return user.build();
    }

    @Override
    public void copyUser(ReplaceUserDto source, User target) {
        if ( source == null ) {
            return;
        }

        target.setName( source.getName() );
    }
}
