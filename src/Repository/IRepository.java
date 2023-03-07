package Repository;
import ADT.PrgState;
import Exception.MyException;

import java.util.List;

public interface IRepository {
    void addPrgState(PrgState var1);
    void logPrgStateExec(PrgState state) throws MyException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);
    PrgState getProgramWithId(int id);
}
