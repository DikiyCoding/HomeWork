package example.homework.activities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import example.homework.R;
import example.homework.classes.CarListAdapter;
import example.homework.classes.ListItem;
import example.homework.classes.MapFunction;
import example.homework.classes.comparators.SortByLocation;
import example.homework.classes.comparators.SortByYear;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CarListAdapter adapter;
    private List<ListItem> items, oldItems, newItems;
    private SortByYear comparatorNumerical;
    private SortByLocation comparatorLiteral;
    private Observer<ListItem> observer;
    private Observable<ListItem> observable;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        RecyclerView list = findViewById(R.id.list);
        setItems();

        newItems = new ArrayList<>();
        oldItems = new ArrayList<>(items);

        adapter = new CarListAdapter(this, items);
        list.setAdapter(adapter);

        Button btnSortNumber = findViewById(R.id.btn_sort_by_number);
        Button btnSortLetter = findViewById(R.id.btn_sort_by_letter);
        btnSortNumber.setOnClickListener(this);
        btnSortLetter.setOnClickListener(this);

        progressBar = findViewById(R.id.progress_bar);

        comparatorNumerical = new SortByYear();
        comparatorLiteral = new SortByLocation();

        createObserver();
    }

    private void setItems() {
        items = new ArrayList<>();
        items.add(new ListItem(1,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_alfa_romeo, 75, 75),
                R.drawable.logo_alfa_romeo,
                "Alfa Romeo",
                "Италия: Турин",
                1910,
                "Легковые автомобили",
                "Джон Элькан — президент, Тим Кунискис — CEO"));
        items.add(new ListItem(2,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_aston_martin, 75, 75),
                R.drawable.logo_aston_martin,
                "Aston Martin",
                "Великобритания: Гейдон (графство Уорикшир)",
                1913,
                "Спортивные автомобили",
                "Ульрих Бец — главный исполнительный управляющий"));
        items.add(new ListItem(1,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_audi, 75, 75),
                R.drawable.logo_audi,
                "Audi",
                "Германия: Ингольштадт",
                1909,
                "Легковые автомобили",
                "Руперт Штадлер (нем. Rupert Stadler) — председатель совета директоров"));
        items.add(new ListItem(3,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_bentley, 75, 75),
                R.drawable.logo_bentley,
                "Bentley",
                "Великобритания: Кру (графство Чешир)",
                1919,
                "Легковые автомобили",
                "Франц-Йозеф Пэфген (нем. Franz-Josef Paefgen) — председатель правления"));
        items.add(new ListItem(4,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_bmw, 75, 75),
                R.drawable.logo_bmw,
                "BMW",
                "Германия: Мюнхен",
                1916,
                "Автомобили, мотоциклы",
                "Норберт Райтхофер — председатель совета директоров"));
        items.add(new ListItem(5,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_bugatti, 75, 75),
                R.drawable.logo_bugatti,
                "Bugatti",
                "Франция: Мольсем",
                1909,
                "Суперкары, гиперкары, автомобили класса люкс, гоночные автомобили",
                "Этторе Бугатти, Жан Бугатти, Роланд Бугатти, Романо Артиоли, Фердинанд Пиех"));
        items.add(new ListItem(6,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_chevrolet, 75, 75),
                R.drawable.logo_chevrolet,
                "Chevrolet",
                "США: Детройт, Мичиган",
                1911,
                "Легковые и грузовые автомобили",
                "Луи Шевроле, Уильям Дюрант"));
        items.add(new ListItem(7,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_dodge, 75, 75),
                R.drawable.logo_dodge,
                "Dodge",
                "США: Оберн-Хиллс, Мичиган",
                1900,
                "Легковые и коммерческие автомобили",
                "Серджио Маркионне — генеральный директор FCA US LLC, Ральф Джиллес — генеральный директор Dodge)"));
        items.add(new ListItem(8,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_ferrari, 75, 75),
                R.drawable.logo_ferrari,
                "Ferrari",
                "Италия: Маранелло",
                1929,
                "Гоночные автомобили и cуперкары",
                "Джон Элканн — президент, председатель совета директоров, Пьеро Феррари — вице-президент, Амедео Фелиса — генеральный директор"));
        items.add(new ListItem(9,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_ford, 75, 75),
                R.drawable.logo_ford,
                "Ford",
                "США: Дирборн, Мичиган",
                1903,
                "Легковые и коммерческие автомобили",
                "Вильям Форд мл. — председатель совета директоров, Джим Хакетт — президент"));
        items.add(new ListItem(10,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_jaguar, 75, 75),
                R.drawable.logo_jaguar,
                "Jaguar",
                "Великобритания: Ковентри",
                1922,
                "Легковые автомобили",
                "Сайрус Мистри — председатель совета директоров Tata Group, Ральф Спет — CEO Jaguar Land Rover"));
        items.add(new ListItem(11,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_lamborghini, 75, 75),
                R.drawable.logo_lamborghini,
                "Lamborghini",
                "Италия: Сант'Агата-Болоньезе",
                1963,
                "Спортивные автомобили, тракторы",
                "Стефано Доменикали — генеральный директор"));
        items.add(new ListItem(12,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_land_rover, 75, 75),
                R.drawable.logo_land_rover,
                "Land Rover",
                "Великобритания",
                1948,
                "Легковые автомобили",
                "Сайрус Мистри — председатель совета директоров Tata Group, Ральф Спет — CEO Jaguar Land Rover"));
        items.add(new ListItem(13,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_lexus, 75, 75),
                R.drawable.logo_lexus,
                "Lexus",
                "Япония: Нагоя",
                1989,
                "Престижные автомобили",
                "Токуо Фукуичи — генеральный директор, Винс Сокко — вице-президент"));
        items.add(new ListItem(14,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_maserati, 75, 75),
                R.drawable.logo_maserati,
                "Maserati",
                "Италия: Модена",
                1914,
                "Элитные легковые автомобили",
                "Серджио Маркионне — председатель совета директоров, Роберто Ронки — главный управляющий"));
        items.add(new ListItem(15,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_mazda, 75, 75),
                R.drawable.logo_mazda,
                "Mazda",
                "Япония: Хиросима",
                1920,
                "Автомобили",
                "Кадзухидэ Ватанабэ — председатель совета директоров, Хисакадзу Имаки — президент, исполнительный директор"));
        items.add(new ListItem(16,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_mercedes_benz, 75, 75),
                R.drawable.logo_mercedes_benz,
                "Mercedes Benz",
                "Германия: Штутгарт",
                1926,
                "Легковые автомобили, грузовики, автобусы, двигатели",
                "Дитер Цетше — председатель совета директоров"));
        items.add(new ListItem(17,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_mitsubishi, 75, 75),
                R.drawable.logo_mitsubishi,
                "Mitsubishi",
                "Япония: Минато, Токио",
                1870,
                "Автомобили",
                "Такаси Нисиока — председатель совета директоров, Осаму Масуко — президент"));
        items.add(new ListItem(18,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_opel, 75, 75),
                R.drawable.logo_opel,
                "Opel",
                "Германия: Рюссельсхайм",
                1862,
                "Легковые автомобили",
                "Карл-Томас Нойманн — президент"));
        items.add(new ListItem(19,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_porsche, 75, 75),
                R.drawable.logo_porsche,
                "Porsche",
                "Германия: Штутгарт",
                1931,
                "Автомобили",
                "Вольфганг Порше"));
        items.add(new ListItem(20,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_rolls_royce, 75, 75),
                R.drawable.logo_rolls_royce,
                "Rolls Royce",
                "Великобритания: Гудвуд, Чичестер (графство Западный Суссекс)",
                1904,
                "Автомобили класса «люкс»",
                "Уоррен Ист — CEO"));
        items.add(new ListItem(21,
                decodeSampledBitmapFromResource(getResources(), R.drawable.logo_subaru, 75, 75),
                R.drawable.logo_subaru,
                "Subaru",
                "Япония: Ота, Токио",
                1954,
                "Легковые автомобили",
                "Кэндзи Кита — основатель группы Subaru"));
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void createObservable(Comparator comparator) {
        observable = Observable.fromIterable(items)
                               .observeOn(Schedulers.newThread())      //отдельный поток
                               .sorted(comparator)                     //сортируем элементы
                               .take(12)                               //первые 12 элементов
                               .map(new MapFunction());                //добавляем длину строки в TextView имени элемента
    }

    private void createObserver() {

        observer = new Observer<ListItem>() {

            @Override
            public void onNext(ListItem item) {
                newItems.add(item);
            }

            @Override
            public void onComplete() {
                items.clear();
                items.addAll(newItems);
                newItems.clear();
            }

            @Override
            public void onError(Throwable exception) {}

            @Override
            public void onSubscribe(Disposable disposable) {
                progressBar.setVisibility(View.VISIBLE);
            }
        };
    }

    @Override
    public void onClick(View view) {
        items.clear();
        items.addAll(oldItems);
        switch (view.getId()) {
            case R.id.btn_sort_by_number:
                createObservable(comparatorNumerical);
                break;
            case R.id.btn_sort_by_letter:
                createObservable(comparatorLiteral);
        }
        observable.subscribe(observer);
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();                 //Я знаю что это полная дичь, тут впринципе всё из говна и палок
    }                                                   //Со списками полный пи*дец, который час охлаждаю своё трахание
}                                                       //Мне самому больно, но что поделать :)
