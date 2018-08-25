#import "TableViewController.h"
#import "ViewController.h"

@interface TableViewController ()

@end

@implementation TableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    _Descrip = @[@"12ga",@"Fires 2 3/4inch and 3inch shells", @"Turkish Walnut Stock", @"Fixed IC", @"Single Trigger", @"Made for us by Sulun Arms of Turkey"];
    
    _specification = @[@"SKU: DOMBANDIT",@"MAKE: Dominion Arms", @"MODEL: Bandit", @"CALIBRE: 12ga", @"BARREL LENGTH: 14", @"MAGAZINE CAPACITY: 2"];
    
    _pageData = @[@"gunOne.jpg", @"gunTwo.jpg", @"gunThree.jpg", @"gunFour.jpg"];
    
    _pageViewController = [[UIPageViewController alloc]
                           initWithTransitionStyle:UIPageViewControllerTransitionStyleScroll
                           navigationOrientation:UIPageViewControllerNavigationOrientationHorizontal
                           options:nil];
    
    _pageViewController.delegate = self;
    _pageViewController.dataSource = self;
    
    ViewController *startingViewController = [self viewControllerAtIndex:0 storyboard:self.storyboard];
    NSArray *viewControllers = @[startingViewController];
    
    [_pageViewController setViewControllers:viewControllers direction:UIPageViewControllerNavigationDirectionForward animated:NO completion:nil];
    
    [self addChildViewController:_pageViewController];
    [self.view addSubview:_pageViewController.view];
    
    // Set the page view controller's bounds using an inset rect so that self's view is visible around the edges of the pages.
    CGRect pageViewRect = self.view.bounds;
    _pageViewController.view.frame = pageViewRect;
    
    [_pageViewController didMoveToParentViewController:self];
    
    UIPageControl *pageControl = [UIPageControl appearance];
    
    pageControl.frame = CGRectMake(110,5,100,140);
    
    pageControl.pageIndicatorTintColor = [UIColor lightGrayColor];
    pageControl.currentPageIndicatorTintColor = [UIColor blackColor];
    pageControl.backgroundColor = [UIColor whiteColor];
    
    CGRect labeLocation = CGRectMake(16, 210, 150, 50);
    UILabel *label = [[UILabel alloc]initWithFrame:labeLocation];
    label.text = @"DOMINION ARMS";
    label.font = [UIFont fontWithName:@"Helvetica-Bold" size:14];
    label.textColor = [UIColor lightGrayColor];
    [self.view addSubview:label];
    
    CGRect labeLocation1 = CGRectMake(16, 228, 230, 60);
    UILabel *label1 = [[UILabel alloc]initWithFrame:labeLocation1];
    label1.text = @"Bandit SXS Shotgun";
    label1.textColor = [UIColor blackColor];
    label1.font = [UIFont fontWithName:@"Helvetica-Bold" size:18];
    [self.view addSubview:label1];
    
    CGRect labeLocation2 = CGRectMake(240, 228, 105, 60);
    UILabel *label2 = [[UILabel alloc]initWithFrame:labeLocation2];
    label2.text = @"$499.99";
    label2.font = [UIFont fontWithName:@"Helvetica-Bold" size:18];
    label2.textAlignment = NSTextAlignmentLeft;
    label2.textColor = [UIColor redColor];
    [self.view addSubview:label2];
    
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    if (section == 0)
        return 1.0f;
    return 40.0f;
}



- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    
    UIView *view;
    UITableViewCell *headerCell = [tableView dequeueReusableCellWithIdentifier:@"headerCell"];
    
    UIFont *font = [UIFont fontWithName:@"HelveticaNeue-bold" size:20];
    headerCell.textLabel.backgroundColor = [UIColor groupTableViewBackgroundColor];
    headerCell.textLabel.font = font;
    headerCell.textLabel.textColor = [UIColor redColor];
    headerCell.textLabel.opaque = NO;
    
    
    switch(section) {
        case 0 : {
            headerCell = nil;
            view = [[UIView alloc]
                    initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, 400)];
            CGFloat width = self.view.bounds.size.width;
            CGFloat height = 220;
            CGRect pageViewRect = CGRectMake(0, 0, width, height);
            _pageViewController.view.frame = pageViewRect;
            [_pageViewController didMoveToParentViewController:self];
            self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        };break;
        case 1 : {
            headerCell.textLabel.text = @"DESCRIPTIONS";break;
        }
        case 2 : {
            headerCell.textLabel.text = @"SPECIFICATIONS";break;
        }
        default: break;
    }
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    return headerCell;
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSInteger i = 0;
    
    switch(section) {
            
        case 0 : i = 6; break;
        case 1 : i = 6; break;
        case 2 : i = 6; break;
            
        default: break;
    }
    return i;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"infoCell" forIndexPath:indexPath];
    
    // Configure the cell...
    UIColor *tcolor = [UIColor grayColor];
    
    if(indexPath.section == 0) {
        cell.textLabel.text = @" ";
    }
    else if(indexPath.section == 1) {
        NSUInteger row = [indexPath row];
        cell.textLabel.text = [_Descrip objectAtIndex:row];
        cell.textLabel.textColor = tcolor;
        self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    }
    else if(indexPath.section == 2) {
        NSUInteger row = [indexPath row];
        cell.textLabel.text = [_specification objectAtIndex:row];
        cell.textLabel.textColor = tcolor;
        self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    }
    
    
    return cell;
}


- (ViewController *)viewControllerAtIndex:(NSUInteger)index storyboard:(UIStoryboard *)storyboard
{
    if (([_pageData count] == 0) || (index >= [_pageData count])) {
        return nil;
    }
    
    ViewController *viewController = [storyboard instantiateViewControllerWithIdentifier:@"ViewController"];
    viewController.dataObject = _pageData[index];
    printf("%lu",(unsigned long)index);
    return viewController;
}

- (NSUInteger)indexOfViewController:(ViewController *)viewController
{
    return [_pageData indexOfObject:viewController.dataObject];
}

#pragma mark - Page View Controller Data Source

- (UIViewController *)pageViewController:(UIPageViewController *)pageViewController viewControllerBeforeViewController:(UIViewController *)viewController
{
    NSUInteger index = [self indexOfViewController:(ViewController *)viewController];
    if ((index == 0) || (index == NSNotFound)) {
        return nil;
    }
    
    index--;
    
    return [self viewControllerAtIndex:index storyboard:viewController.storyboard];
}

- (UIViewController *)pageViewController:(UIPageViewController *)pageViewController viewControllerAfterViewController:(UIViewController *)viewController
{
    NSUInteger index = [self indexOfViewController:(ViewController *)viewController];
    if (index == NSNotFound) {
        return nil;
    }
    
    index++;
    if (index == [self.pageData count]) {
        return nil;
    }
    
    return [self viewControllerAtIndex:index storyboard:viewController.storyboard];
}

- (NSInteger)presentationCountForPageViewController:(UIPageViewController *)pageViewController
{
    return [_pageData count];   // number of pages
}


- (NSInteger)presentationIndexForPageViewController:(UIPageViewController *)pageViewController
{
    return 0;   // index of the first page
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end

