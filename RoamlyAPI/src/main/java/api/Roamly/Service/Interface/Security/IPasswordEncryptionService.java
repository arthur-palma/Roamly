package api.Roamly.Service.Interface.Security;

public interface IPasswordEncryptionService {
    public String encrypt(String password);

    public boolean matches(String rawPassword, String hashedPassword);

}
