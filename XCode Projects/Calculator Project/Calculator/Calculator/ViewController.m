

#import "ViewController.h"

@interface ViewController ()
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    _userIsInTheMiddleOfEnteringNumber = NO;
    
    _behindTheDecimal = NO;
    
    if(!_model) {
        _model = [[CalculatorModel alloc] init];
    }
}

- (UIStatusBarStyle)preferredStatusBarStyle {
    
    return UIStatusBarStyleLightContent;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (IBAction)digitPressed:(id)sender {
    NSString *digit = [sender currentTitle];
    
    if(_userIsInTheMiddleOfEnteringNumber) {
        if(_val == 0 && [digit isEqualToString:@"0"]) {
            _display.text = @"0";
        }
        else if(_val == 0 && ![digit isEqualToString:@"0"]) {
            _display.text = digit;
        }
        else {
            _display.text = [_display.text stringByAppendingString:digit];
        }
        _val = [_display.text floatValue];
    }
    else {
        _display.text = digit;
        _val = [digit floatValue];
        _userIsInTheMiddleOfEnteringNumber = YES;
    }
}

- (IBAction)specialDigitPressed:(id)sender {
    NSString *btn = [sender currentTitle];
    
    if([btn isEqualToString:@"√"])
    {
        float number = 0;
        number = sqrt([_display.text floatValue]);
        NSString *myNewString = [NSString stringWithFormat:@"%f", number];
        _display.text = myNewString;
        _behindTheDecimal = NO;
        _userIsInTheMiddleOfEnteringNumber = NO;
    }
    
    else if([btn isEqualToString:@"sign"])
    {
        float number = 0;
        number = ([_display.text floatValue] * -1);
        NSString *myNewString = [NSString stringWithFormat:@"%f", number];
        _display.text = myNewString;
        _behindTheDecimal = NO;
        _userIsInTheMiddleOfEnteringNumber = NO;
    }
    
    else if([btn isEqualToString:@"x^2"])
    {
        float number = 0;
        number = pow([_display.text floatValue],2);
        NSString *myNewString = [NSString stringWithFormat:@"%f", number];
        _display.text = myNewString;
        _behindTheDecimal = NO;
        _userIsInTheMiddleOfEnteringNumber = NO;
    }
    
    else if([btn isEqualToString:@"."])
    {
        if(_behindTheDecimal == YES)
            return;
        
        int number = 0;
        number = ([_display.text intValue]);
        NSString *myNewString = [NSString stringWithFormat:@"%d", number];
        _display.text = [myNewString stringByAppendingString:@"."];
        _behindTheDecimal = YES;
        _val = [_display.text floatValue];
    }
}

- (IBAction)acPressed:(id)sender {
    _operand = [sender currentTitle];
    [_model pushOperand:[_display.text floatValue]];
    _result = [_model performOperation:_operand];
    _userIsInTheMiddleOfEnteringNumber = NO;
    _display.text = @"0";
    _behindTheDecimal = NO;
}


- (IBAction)equalPressed:(id)sender {
    _operand = [sender currentTitle];
    [_model pushOperand:[_display.text floatValue]];
    _result = [_model performOperation:_operand];
    _userIsInTheMiddleOfEnteringNumber = NO;
    _display.text = _result;
    _behindTheDecimal = NO;
}

- (IBAction)operationPressed:(id)sender {
    _operand = [sender currentTitle];
    [_model pushOperand:[_display.text floatValue]];
    _result = [_model performOperation:_operand];
    _behindTheDecimal = NO;
    _userIsInTheMiddleOfEnteringNumber = NO;
}
@end
