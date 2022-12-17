import { CheckIcon, CloseIcon } from '@chakra-ui/icons'

interface LabelBooleanProps {
    value: boolean;
}

const LabelBoolean = ({ value }: LabelBooleanProps) => {
    
  if(value){
    return (
      <CheckIcon />
    )  
  }
  return (
    <CloseIcon />
  );
}

export default LabelBoolean;