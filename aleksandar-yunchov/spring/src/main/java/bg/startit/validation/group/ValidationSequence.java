package bg.startit.validation.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(value = {Default.class, SecondExecuteGroup.class, ThirdExecuteGroup.class})
public interface ValidationSequence
{
}
