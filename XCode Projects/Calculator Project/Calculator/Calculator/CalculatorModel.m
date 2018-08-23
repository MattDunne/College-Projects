

#import "CalculatorModel.h"

@implementation CalculatorModel

-(id) init {
    _result = 0;
    _lastOperator = @" ";
    return self;
}

-(void)pushOperand:(float) operand {
    _operand = operand;
}

-(NSString *)performOperation:(NSString *)operator {
    
    if([operator isEqualToString:@"*"]) {
        [self compute];
        _lastOperator = @"*";
    }
    
    if([operator isEqualToString:@"/"]) {
        [self compute];
        _lastOperator = @"/";
    }
    
    if([operator isEqualToString:@"+"]) {
        [self compute];
        _lastOperator = @"+";
    }
    
    if([operator isEqualToString:@"-"]) {
        [self compute];
        _lastOperator = @"-";
    }
    
    if([operator isEqualToString:@"="]) {
        [self compute];
        _lastOperator = @" ";
    }
    
    if([operator isEqualToString:@"AC"]) {
        [self compute];
        _lastOperator = @" ";
    }
    
    return _returnResult;
}

-(void)compute {
    
    if([_lastOperator isEqualToString:@" "]) {
        _result = _operand;
        _returnResult = [NSString stringWithFormat:@"%f", _result];
    }
    else if([_lastOperator isEqualToString:@"*"]) {
        _result = _result * _operand;
        _returnResult = [NSString stringWithFormat:@"%f", _result];
    }
    else if([_lastOperator isEqualToString:@"/"]) {
        if(_operand == 0)
            _returnResult = @"NaN";
        else {
            _result = _result / _operand;
            _returnResult = [NSString stringWithFormat:@"%f", _result];
        }
    }
    else if([_lastOperator isEqualToString:@"+"]) {
        _result = _result + _operand;
        _returnResult = [NSString stringWithFormat:@"%f", _result];
    }
    else if([_lastOperator isEqualToString:@"-"]) {
        _result = _result - _operand;
        _returnResult = [NSString stringWithFormat:@"%f", _result];
    }
}

@end
