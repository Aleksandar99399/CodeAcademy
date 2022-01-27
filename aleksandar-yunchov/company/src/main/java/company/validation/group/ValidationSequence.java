package company.validation.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(value = {Default.class, SecondExecuteGroup.class})
public interface ValidationSequence
{
}
