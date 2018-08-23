

#import <Foundation/Foundation.h>

@interface CalculatorModel : NSObject

@property float result;
@property float operand;
@property NSString *lastOperator;
@property NSString *returnResult;

-(void)pushOperand:(float) operand;
-(NSString *)performOperation:(NSString *)operator;

@end
